package com.example.lastmyspaza.Admin.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Store;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<ManagerDetails> accList;
    private ArrayList<Store> stores;

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView title, firstName, lastName, storeLocation, email, viewUser;
        public TextView storeLocation, storeName;

        public MyViewHolder(View view) {
            super(view);
            storeLocation = view.findViewById(R.id.store_name);
            storeName = view.findViewById(R.id.store_location);

//            title =  view.findViewById(R.id.title);
//            firstName =  view.findViewById(R.id.first_name);
//            lastName =  view.findViewById(R.id.last_name);
//            email =  view.findViewById(R.id.email);
//            storeLocation =  view.findViewById(R.id.store_location);
//            viewUser = view.findViewById(R.id.view);
        }
    }


    public UsersAdapter(ArrayList<Store> stores) {
        this.stores = stores;
        //this.accList = accList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        ManagerDetails user = accList.get(position);
//        holder.title.setText(user.getStoreName());
//        holder.firstName.setText(user.getFirstName());
//        holder.lastName.setText(user.getLastName());
//        holder.email.setText(user.getEmail());
//        holder.storeLocation.setText(user.getStoreLocation());
        Store store = stores.get(position);
        holder.storeName.setText(store.getStoreName());
        holder.storeLocation.setText(store.getStoreLocation());

    }

    @Override
    public int getItemCount() {
        return stores.size();
    }
}