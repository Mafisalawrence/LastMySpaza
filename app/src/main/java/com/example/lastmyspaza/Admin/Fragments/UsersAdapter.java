package com.example.lastmyspaza.Admin.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<ManagerDetails> accList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, firstName, lastName, storeLocation, email, viewUser;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            firstName = (TextView) view.findViewById(R.id.first_name);
            lastName = (TextView) view.findViewById(R.id.last_name);
            email = (TextView) view.findViewById(R.id.email);
            storeLocation = (TextView) view.findViewById(R.id.store_location);
            viewUser = (TextView) view.findViewById(R.id.view);
        }
    }


    public UsersAdapter(List<ManagerDetails> accList) {
        this.accList = accList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ManagerDetails user = accList.get(position);
        holder.title.setText(user.getStoreName());
        holder.firstName.setText(user.getFirstName());
        holder.lastName.setText(user.getLastName());
        holder.email.setText(user.getEmail());
        holder.storeLocation.setText(user.getStoreLocation());
    }

    @Override
    public int getItemCount() {
        return accList.size();
    }
}