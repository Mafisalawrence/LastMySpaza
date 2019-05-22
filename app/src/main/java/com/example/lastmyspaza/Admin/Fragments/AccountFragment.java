package com.example.lastmyspaza.Admin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountFragment extends Fragment {


    private List<ManagerDetails> accList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UsersAdapter userAdapter;

    public AccountFragment() {
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

        userAdapter = new UsersAdapter(accList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        prepareUserData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void prepareUserData() {

        DatabaseIteration dbIteration = new DatabaseIteration(this.getContext());
        dbIteration.getAccountDetailList("users", new OnGetDataListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(DataSnapshot data) {
                Log.d("managerDetails", data.getValue().toString());
                
//                ManagerDetails managerDetails = data.getValue(ManagerDetails.class);
//                accList.add(managerDetails);
//                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                //DO SOME THING WHEN GET DATA FAILED HERE
            }
        });
    }
}