package com.example.lastmyspaza.Admin.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lastmyspaza.R;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<Users> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, userType, viewUser;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userType = (TextView) view.findViewById(R.id.userType);
            viewUser = (TextView) view.findViewById(R.id.view);
        }
    }


    public UsersAdapter(List<Users> userList) {
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Users user = userList.get(position);
        holder.title.setText(user.getEmail());
        holder.userType.setText(user.getUsertype());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
