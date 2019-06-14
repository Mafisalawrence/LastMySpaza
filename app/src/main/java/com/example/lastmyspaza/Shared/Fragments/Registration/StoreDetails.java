package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lastmyspaza.Manager.Activities.ManagerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Store;
import com.example.lastmyspaza.Shared.ViewModel.RegistrationAccountDetails;
import com.example.lastmyspaza.Shared.ViewModel.StoreListViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static android.content.ContentValues.TAG;

public class StoreDetails extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText mStoreName;
    private EditText mStoreLocation;
    private Button mSignUp;
    private RelativeLayout autoSuggestWrapper;
    private AutoCompleteTextView autoCompleteTextView;
    private DatabaseIteration databaseIteration;
    private Authentication authentication;
    private StoreListViewModel StoreListViewModel;
    private ManagerDetails managerDetails;
    private RegistrationAccountDetails registrationAccountDetails;
    private ArrayList<Store> storesFromDB = new ArrayList<>();
    private  StoreAutoCompleteAdapter autoCompleteAdapter;
    private Store registrationStore;
    private String currentUserRole;

    public StoreDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_strore_details, container, false);
        loadUIComponents(view);


        authentication = new Authentication(getContext());
        databaseIteration = new DatabaseIteration(getContext());
        //registrationAccountDetails = ViewModelProviders.of(getActivity()).get(RegistrationAccountDetails.class);
        managerDetails = registrationAccountDetails.getManagerDetails();

        if (managerDetails.getRole().equals(Roles.Manager.toString()))
        {
            mSignUp.setText("Sign Up");
            mStoreName.setVisibility(View.GONE);
            autoSuggestWrapper.setVisibility(View.VISIBLE);
        }

        autoCompleteAdapter  =  new StoreAutoCompleteAdapter(getContext(),R.layout.store_list_item,storesFromDB);

        databaseIteration.getAllStores("stores", new OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                for(DataSnapshot value : data.getChildren()) {
                    Store store = value.getValue(Store.class);
                    store.setStoreId(value.getKey());
                    storesFromDB.add(store);
                }
                autoCompleteAdapter  =  new StoreAutoCompleteAdapter(getContext(),R.layout.store_list_item,storesFromDB);
                autoCompleteTextView.setThreshold(1); //will start working from first character
                autoCompleteTextView.setAdapter(autoCompleteAdapter);
                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        registrationStore = (Store) adapterView.getItemAtPosition(i);
                        mStoreLocation.setText(registrationStore.getStoreLocation());
                        mStoreName.setText(registrationStore.getStoreName());
                    }
                });
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.d(TAG,databaseError.toString());
            }
        });


        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Store store = new Store();
                store.setStoreName(mStoreName.getText().toString());
                store.setStoreLocation(mStoreLocation.getText().toString());

                if(managerDetails.getRole().equals(Roles.Admin.toString()))
                {
                    //StoreListViewModel = ViewModelProviders.of(getActivity()).get(StoreListViewModel.class);
                    StoreListViewModel.setStore(store);

                    FragmentManager fragmentManager =  getFragmentManager();
                    fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("storeDetails"))
                            .show( fragmentManager.findFragmentByTag("storeList"))
                            .commit();
                }
                else{
                    authentication.CreateManagerAccount(managerDetails.getEmail(),registrationAccountDetails.getPassword())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if ( task.isSuccessful()){
                                        String userId = authentication.GetCurrentUser().getUid();
                                        registrationStore.setStoreManager(userId);
                                        databaseIteration.addManagerToStore(userId,registrationStore);
                                        addManagerDetailsToDb(userId);
                                    }else
                                    {
                                        Toast.makeText(getContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
        return view;
    }

    private void loadUIComponents(View view)
    {
        mStoreName = view.findViewById(R.id.store_name);
        mStoreLocation = view.findViewById(R.id.store_location);
        autoCompleteTextView = view.findViewById(R.id.autocompleteTextView);
        mSignUp = view.findViewById(R.id.add_storeDetails);
        autoSuggestWrapper = view.findViewById(R.id.auto_suggest_wrapper);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public void addManagerDetailsToDb(String uid)
    {
        databaseIteration.addManagerInformationToDb(uid,managerDetails)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            //TODO SAVE ROLE IN SHARED Preferenceces and remove when logging out

                            Intent intent = new Intent(getActivity(), ManagerActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getContext(),"Failed to add manager details to db", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void getAllStores()
    {
        databaseIteration.getAllStores("stores", new OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                for(DataSnapshot value : data.getChildren()) {
                    Store store = value.getValue(Store.class);
                    store.setStoreId(value.getKey());
                    //storesFromDB.add(store);
                    autoCompleteAdapter.add(store);

                }
                autoCompleteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        });

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
