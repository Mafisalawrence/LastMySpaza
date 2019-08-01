package com.example.lastmyspaza.Shared.Fragments.Registration;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lastmyspaza.Owner.OwnerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Interfaces.OnStoreListLister;
import com.example.lastmyspaza.Shared.Models.AccountDetails;
import com.example.lastmyspaza.Shared.Models.Store;
import com.example.lastmyspaza.Shared.ViewModel.RegistrationAccountDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreList extends Fragment {

    private ArrayList<Store> stores = new ArrayList<>();
    private StoresListAdapter storeListAdapter;
    private AccountDetails accountDetails;
  //  private StoreListViewModel StoreListViewModel;
    private RegistrationAccountDetails registrationAccountDetails;
    private DatabaseIteration databaseIteration;
    private Authentication authentication;
    private Button done;
    private Button addDetails;
    private String currentUserId;
    public StoreList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        addDetails = view.findViewById(R.id.add_storeDetails);
        done = view.findViewById(R.id.done);

       // StoreListViewModel = ViewModelProviders.of(getActivity()).get(StoreListViewModel.class);
        //registrationAccountDetails = ViewModelProviders.of(getActivity()).get(RegistrationAccountDetails.class);
       // accountDetails = registrationAccountDetails.getAccountDetails();
        databaseIteration = new DatabaseIteration(getContext());
        authentication =  new Authentication(getContext());


        storeListAdapter = new StoresListAdapter(stores, new OnStoreListLister() {
            @Override
            public void deleteItem(Store store,int i) {
                stores.remove(i);
                storeListAdapter.notifyItemRemoved(i);
            }
        });

//        EmptyRecyclerView list = view.findViewById(R.id.store_list);
//        list.setLayoutManager(new LinearLayoutManager(getContext()));
//        list.setEmptyView(view.findViewById(R.id.empty_list));
//        list.setAdapter(storeListAdapter);
//
//        StoreListViewModel.getStore().observe(this, new Observer<Store>() {
//            @Override
//            public void onChanged(@Nullable Store store) {
//                if(currentUserId.isEmpty() && currentUserId != null)done.setVisibility(View.VISIBLE);
//                stores.add(store);
//                storeListAdapter.notifyDataSetChanged();
//            }
//        });
        //currentUserId = authentication.GetCurrentUser().getUid();
        //getAllStoresOFOwner(currentUserId);

//        addDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().add(R.id.fragment_container, new StoreDetails(),"storeDetails")
//                        .hide(fragmentManager.findFragmentByTag("storeList"))
//                        .commit();
//            }
//        });
//
//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                accountDetails = registrationAccountDetails.getAccountDetails();
//                authentication.CreateManagerAccount(accountDetails.getEmail(),registrationAccountDetails.getPassword())
//                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                 if ( task.isSuccessful()){
//                                    String userId = authentication.GetCurrentUser().getUid();
//                                    addStoreDetailsToDb(userId);
//                                    addManagerDetailsToDb(userId);
//                                }else
//                                {
//                                    Toast.makeText(getContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
            //}
        //});

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void addStoreDetailsToDb(String uid)
    {
        for(Store store : stores)
        {
            store.setStoreOwner(uid);
        }
        databaseIteration.addStoresDetailsToDb(stores);
    }
    public void addManagerDetailsToDb(String uid)
    {
        databaseIteration.addManagerInformationToDb(uid, accountDetails)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent = new Intent(getActivity(), OwnerActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getContext(),"Failed to add manager details to db", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
//    private void getAllStoresOFOwner(String uid) {
//
//        DatabaseIteration dbIteration = new DatabaseIteration(this.getContext());
//        dbIteration.getAllStoresOfOwner("stores",uid, new OnGetDataListener() {
//            @Override
//            public void onStart() {
//            }
//
//            @Override
//            public void onSuccess(DataSnapshot data) {
//                for(DataSnapshot value : data.getChildren()) {
//                    Store store = value.getValue(Store.class);
//                    StoreListViewModel.setStore(store);
//                }
//            }
//
//            @Override
//            public void onFailed(DatabaseError databaseError) {
//                Log.d(TAG,databaseError.toString());
//            }
//        });
//    }

}
