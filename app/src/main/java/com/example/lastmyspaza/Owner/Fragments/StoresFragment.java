package com.example.lastmyspaza.Owner.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Fragments.Registration.StoreDetails;
import com.example.lastmyspaza.Shared.Fragments.Registration.StoresListAdapter;
import com.example.lastmyspaza.Shared.Interfaces.OnStoreListLister;
import com.example.lastmyspaza.Shared.Models.Store;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StoresFragment extends Fragment {

    private ArrayList<Store> stores =  new ArrayList<>();
    private RecyclerView recyclerView;
    private Button addStore;
    private StoresListAdapter StoresListAdapter;
    public StoresFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        addStore = rootView.findViewById(R.id.add_store);

        Store s =  new Store("kabelo kota", "soweto");
        Store st = new Store("yebo name", "Braam");
        Store sss = new Store("tripple s","Orlando");
        stores.add(s);
        stores.add(st);
        stores.add(sss);

        StoresListAdapter = new StoresListAdapter(stores, new OnStoreListLister() {
            @Override
            public void deleteItem(Store store, int i) {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle(R.string.confirm_remove_store_title)
                        .setMessage(R.string.confirm_remove_store)
                        .setNegativeButton("Cancel",null)
                        .setPositiveButton("Ok", null)
                        .show();
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(StoresListAdapter);

        addStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new StoreDetails())
                        .commit();
            }
        });
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        authentication = new Authentication(getContext());
//        String uid = authentication.GetCurrentUser().getUid();
//        getAllStoresOFOwner(uid);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}