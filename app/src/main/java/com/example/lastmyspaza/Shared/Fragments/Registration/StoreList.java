package com.example.lastmyspaza.Shared.Fragments.Registration;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Interfaces.OnStoreListLister;
import com.example.lastmyspaza.Shared.Models.Store;
import com.example.lastmyspaza.Shared.ViewModel.RegistrationAccountDetails;
import com.example.lastmyspaza.Shared.ViewModel.StoreListViewModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreList extends Fragment {

    private RecyclerView recyclerView;
    private StoresListAdapter storesListAdapter;
    private Button done;
    public StoreList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        done = view.findViewById(R.id.done);
        recyclerView = view.findViewById(R.id.store_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        StoreListViewModel storeListViewModel = ViewModelProviders.of(getActivity()).get(StoreListViewModel.class);
        final RegistrationAccountDetails registrationAccountDetails = ViewModelProviders.of(getActivity()).get(RegistrationAccountDetails.class);
        storeListViewModel.getStoreList().observe(this, new Observer<List<Store>>() {
            @Override
            public void onChanged(@Nullable List<Store> stores) {
                storesListAdapter = new StoresListAdapter(stores, new OnStoreListLister() {
                    @Override
                    public void cardSelected(Store store, int i, MaterialCardView cardView) {
                        if (cardView.isChecked()){
                            registrationAccountDetails.setStore(store);
                            done.setEnabled(false);
                            cardView.setChecked(false);}
                        else{
                            done.setEnabled(true);
                            cardView.setChecked(true);
                        }
                    }
                });
                recyclerView.setAdapter(storesListAdapter);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Store s = registrationAccountDetails.getStore();
                Toast.makeText(getContext(),s.getStoreName(),Toast.LENGTH_LONG).show();
                registrationAccountDetails.setRegistrationDetailsPhase2();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}

