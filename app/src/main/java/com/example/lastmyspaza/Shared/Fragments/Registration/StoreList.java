package com.example.lastmyspaza.Shared.Fragments.Registration;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Interfaces.OnStoreListLister;
import com.example.lastmyspaza.Shared.Interfaces.OnItemClickListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Product;
import com.example.lastmyspaza.Shared.Models.Store;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreList extends Fragment {


    private ArrayList<Store> stores = new ArrayList<>();
    private StoresListAdapter storeListAdapter;
    private ManagerDetails managerDetails;
    public StoreList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_list, container, false);
        Button addDetails = view.findViewById(R.id.add_storeDetails);

        Store store = (Store) getArguments().getSerializable("store");
        if(store != null){
            stores.add(store);
        }
        Store s = new Store();
        s.setStoreLocation("d");
        s.setStoreName("y");
        stores.add(s);

        Store ss = new Store();
        ss.setStoreLocation("store");
        ss.setStoreName("locaton");
        stores.add(ss);

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

        managerDetails =  (ManagerDetails) getArguments().getSerializable("managerDetails");

        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreDetails storeDetails = new StoreDetails();
                Bundle bundle =  new Bundle();
                bundle.putSerializable("managerDetails", managerDetails);
                storeDetails.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, storeDetails)
                        .commit();
            }
        });

        return view;
    }

}
