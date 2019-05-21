package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastmyspaza.Manager.Activities.ManagerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Store;
import com.example.lastmyspaza.Shared.ViewModel.RegistrationAccountDetails;
import com.example.lastmyspaza.Shared.ViewModel.StoreListViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;

public class StoreDetails extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText mStoreName;
    private EditText mStoreLocation;
    private Button mSignUp;
    private DatabaseIteration databaseIteration;
    private Authentication authentication;
    private StoreListViewModel StoreListViewModel;
    private ManagerDetails managerDetails;
    private RegistrationAccountDetails registrationAccountDetails;
    private ArrayList<Store> stores = new ArrayList<>();


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
        mStoreName = view.findViewById(R.id.store_name);
        mStoreLocation = view.findViewById(R.id.store_location);
        mSignUp = view.findViewById(R.id.add_storeDetails);

        authentication = new Authentication(getContext());
        databaseIteration = new DatabaseIteration(getContext());

        registrationAccountDetails = ViewModelProviders.of(getActivity()).get(RegistrationAccountDetails.class);
        managerDetails = registrationAccountDetails.getManagerDetails();

//        final Store store = (Store) getArguments().getSerializable("store");
//        if(store != null){
//            mStoreName.setText(store.getStoreName());
//            mStoreLocation.setText(store.getStoreLocation());
//        }

        if (managerDetails.getRole().equals(Roles.Manager.toString()))
        {
            mSignUp.setText("Sign Up");
        }
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Store store = new Store();
                store.setStoreName(mStoreName.getText().toString());
                store.setStoreLocation(mStoreLocation.getText().toString());

                if(managerDetails.getRole().equals(Roles.Admin.toString()))
                {
                    StoreListViewModel = ViewModelProviders.of(getActivity()).get(StoreListViewModel.class);
                    StoreListViewModel.setStore(store);

                    FragmentManager fragmentManager =  getFragmentManager();
                    fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("storeDetails"))
                            .show( fragmentManager.findFragmentByTag("storeList"))
                            .commit();
                }
                else{
                    stores.add(store);
                    managerDetails.setStores(stores);
                    authentication.CreateManagerAccount(managerDetails.getEmail(),registrationAccountDetails.getPassword())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if ( task.isSuccessful()){
                                        String userId = authentication.GetCurrentUser().getUid();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
