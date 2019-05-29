package com.example.lastmyspaza.Shared.Fragments.Registration;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lastmyspaza.Owner.OwnerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Interfaces.OnStoreListLister;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Store;
import com.example.lastmyspaza.Shared.ViewModel.RegistrationAccountDetails;
import com.example.lastmyspaza.Shared.ViewModel.StoreListViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreList extends Fragment {

    private ArrayList<Store> stores = new ArrayList<>();
    private StoresListAdapter storeListAdapter;
    private ManagerDetails managerDetails;
    private StoreListViewModel StoreListViewModel;
    private RegistrationAccountDetails registrationAccountDetails;
    private DatabaseIteration databaseIteration;
    private Authentication authentication;
    private Button done;
    private Button addDetails;
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

        StoreListViewModel = ViewModelProviders.of(getActivity()).get(StoreListViewModel.class);
        registrationAccountDetails = ViewModelProviders.of(getActivity()).get(RegistrationAccountDetails.class);
        managerDetails = registrationAccountDetails.getManagerDetails();
        databaseIteration = new DatabaseIteration(getContext());
        authentication =  new Authentication(getContext());


        storeListAdapter = new StoresListAdapter(stores, new OnStoreListLister() {
            @Override
            public void deleteItem(Store store,int i) {
                stores.remove(i);
                storeListAdapter.notifyItemRemoved(i);
            }

            @Override
            public void editItem(Store store) {
               Bundle bundle = new Bundle();
               StoreDetails storeDetails = new StoreDetails();
               bundle.putSerializable("store",store);
               storeDetails.setArguments(bundle);
               getFragmentManager().beginTransaction()
               .replace(R.id.fragment_container, storeDetails)
               .commit();
            }
        });

        EmptyRecyclerView list = view.findViewById(R.id.store_list);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setEmptyView(view.findViewById(R.id.empty_list));
        list.setAdapter(storeListAdapter);

        StoreListViewModel.getStore().observe(this, new Observer<Store>() {
            @Override
            public void onChanged(@Nullable Store store) {
                done.setVisibility(View.VISIBLE);
                stores.add(store);
                storeListAdapter.notifyDataSetChanged();
            }
        });


        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().add(R.id.fragment_container, new StoreDetails(),"storeDetails")
                        .hide(fragmentManager.findFragmentByTag("storeList"))
                        .commit();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDetails = registrationAccountDetails.getManagerDetails();
                authentication.CreateManagerAccount(managerDetails.getEmail(),registrationAccountDetails.getPassword())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                 if ( task.isSuccessful()){
                                    String userId = authentication.GetCurrentUser().getUid();
                                    addStoreDetailsToDb(userId);
                                    addManagerDetailsToDb(userId);
                                }else
                                {
                                    Toast.makeText(getContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        return view;
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
        databaseIteration.addManagerInformationToDb(uid,managerDetails)
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

}
