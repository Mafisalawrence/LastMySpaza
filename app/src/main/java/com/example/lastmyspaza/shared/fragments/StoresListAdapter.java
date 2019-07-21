package com.example.lastmyspaza.shared.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.shared.Interfaces.OnStoreListLister;
import com.example.lastmyspaza.shared.Models.Store;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class StoresListAdapter extends  RecyclerView.Adapter<StoresListAdapter.MyViewHolder> {

    private ArrayList<Store> stores;
    private OnStoreListLister onStoreListLister;

    public StoresListAdapter(ArrayList<Store> stores, OnStoreListLister onStoreListLister){
        this.stores = stores;
        this.onStoreListLister = onStoreListLister;
    }
    public StoresListAdapter(ArrayList<Store> stores)
    {
        this.stores = stores;
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

        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStoreListLister.deleteItem(stores.get(i),i);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView storeName, storeLocation;
        public ImageView delete;

        public MyViewHolder(View view) {
            super(view);
            storeName = view.findViewById(R.id.store_name);
            storeLocation = view.findViewById(R.id.store_location);
            delete = view.findViewById(R.id.delete);
        }
    }
}

