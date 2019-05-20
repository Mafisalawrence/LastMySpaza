package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Interfaces.OnStoreListLister;
import com.example.lastmyspaza.Shared.Interfaces.OnItemClickListener;
import com.example.lastmyspaza.Shared.Models.Store;

import java.util.ArrayList;
import java.util.List;


public class StoresListAdapter extends  RecyclerView.Adapter<StoresListAdapter.MyViewHolder> {

    private ArrayList<Store> stores;
    private OnStoreListLister onStoreListLister;

    public StoresListAdapter(ArrayList<Store> stores, OnStoreListLister onStoreListLister){
        this.stores = stores;
        this.onStoreListLister = onStoreListLister;
    }


    @Override
    public int getItemCount() {
        return stores == null ? 0 : stores.size();
    }

    @Override
    public void onBindViewHolder(@NonNull StoresListAdapter.MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.store_list_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Store store = stores.get(i);
        myViewHolder.storeName.setText(store.getStoreName());
        myViewHolder.storeLocation.setText(store.getStoreLocation());
        myViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStoreListLister.editItem(stores.get(i));

            }
        });
        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStoreListLister.deleteItem(stores.get(i),i);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView storeName, storeLocation;
        public ImageView edit, delete;

        public MyViewHolder(View view) {
            super(view);
            storeName = view.findViewById(R.id.store_name);
            storeLocation = view.findViewById(R.id.store_location);

            edit = view.findViewById(R.id.edit);
            delete = view.findViewById(R.id.delete);
        }
    }
}

