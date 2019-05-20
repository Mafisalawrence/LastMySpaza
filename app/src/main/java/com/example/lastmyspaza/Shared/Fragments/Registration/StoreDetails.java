package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastmyspaza.Manager.Activities.ManagerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.Models.Store;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class StoreDetails extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText mStoreName;
    private EditText mStoreLocation;
    private Button mSignUp;
    private DatabaseIteration databaseIteration;
    private ManagerDetails managerDetails;
    private Authentication authentication;

    public StoreDetails() {
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
        View view = inflater.inflate(R.layout.fragment_strore_details, container, false);
        mStoreName = view.findViewById(R.id.store_name);
        mStoreLocation = view.findViewById(R.id.store_location);
        mSignUp = view.findViewById(R.id.add_storeDetails);

        authentication = new Authentication(getContext());
        databaseIteration = new DatabaseIteration(getContext());
        managerDetails = (ManagerDetails) getArguments().getSerializable("managerDetails");

        Store store = (Store) getArguments().getSerializable("store");
        if(store != null){
            mStoreName.setText(store.getStoreName());
            mStoreLocation.setText(store.getStoreLocation());
        }
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //managerDetails.setStoreName(mStoreName.getText().toString());
                //managerDetails.setStoreLocation(mStoreLocation.getText().toString());
                String storeName = mStoreName.getText().toString();
                String storeLocation = mStoreLocation.getText().toString();
                Store store = new Store();
                store.setStoreName(storeName);
                store.setStoreLocation(storeLocation);
             //   managerDetails.setStores(stores);

                Bundle bundle =  new Bundle();
                StoreList storeList =  new StoreList();

                bundle.putSerializable("store", store);
                storeList.setArguments(bundle);

                getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, storeList)
                .commit();

//                if(managerDetails.getRole().equals(Roles.Admin.toString())){
//
//                }
                //addManagerDetailsToDb(getArguments().getString("identifier"));
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void addManagerDetailsToDb(String uid)
    {
        databaseIteration.addManagerInformationToDb(uid,managerDetails)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getContext(),"Manager details added to db", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), ManagerActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getContext(),"Failed to add manager details to db", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
