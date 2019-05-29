package com.example.lastmyspaza.Owner.Fragments;

import android.content.Context;
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
import com.example.lastmyspaza.Shared.Fragments.Registration.StoresListAdapter;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class StoresFragment extends Fragment {

    private ArrayList<Store> stores =  new ArrayList<>();
    private RecyclerView recyclerView;
    private StoresListAdapter StoresListAdapter;
    private Authentication authentication;

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

        StoresListAdapter = new StoresListAdapter(stores);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(StoresListAdapter);
        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        authentication = new Authentication(getContext());
        String uid = authentication.GetCurrentUser().getUid();
        getAllStoresOFOwner(uid);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void getAllStoresOFOwner(String uid) {

        DatabaseIteration dbIteration = new DatabaseIteration(this.getContext());
        dbIteration.getAllStoresOfOwner("stores",uid, new OnGetDataListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(DataSnapshot data) {
                for(DataSnapshot value : data.getChildren()) {
                    Store store = value.getValue(Store.class);
                    stores.add(store);
                    StoresListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.d(TAG,databaseError.toString());
            }
        });
    }
}