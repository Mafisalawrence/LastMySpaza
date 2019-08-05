package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Interfaces.OnStoreListLister;
import com.example.lastmyspaza.Shared.Models.Store;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class StoresListAdapter extends  RecyclerView.Adapter<StoresListAdapter.MyViewHolder> {

    private List<Store> stores;
    private OnStoreListLister onStoreListLister;
    private int lastChecked = -1;

    public StoresListAdapter(List<Store> stores, OnStoreListLister onStoreListLister){
        this.stores = stores;
        this.onStoreListLister = onStoreListLister;
    }
    public StoresListAdapter(List<Store> stores)
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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        Store store = stores.get(i);
        myViewHolder.storeName.setText(store.getStoreName());
        myViewHolder.storeLocation.setText(store.getStoreLocation());

        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onStoreListLister.cardSelected(stores.get(i),i,myViewHolder.cardView);
            }
        });
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView storeName, storeLocation;
        public MaterialCardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.store_list_item_card);
            storeName = view.findViewById(R.id.store_name);
            storeLocation = view.findViewById(R.id.store_location);
        }
    }
}

