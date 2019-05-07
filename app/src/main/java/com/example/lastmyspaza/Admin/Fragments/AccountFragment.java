package com.example.lastmyspaza.Admin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private List<ManagerDetails> accList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UsersAdapter userAdapter;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        userAdapter = new UsersAdapter(accList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);

        prepareUserData();

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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


    private void prepareUserData() {

        DatabaseIteration dbIteration = new DatabaseIteration(this.getContext());
        dbIteration.getAccountDetailList("managers", new OnGetDataListener() {
            @Override
            public void onStart() {
                //DO SOME THING WHEN START GET DATA HERE
            }

            @Override
            public void onSuccess(DataSnapshot data) {
                //DO SOME THING WHEN GET DATA SUCCESS HERE
                Map<String, Object> accountList = (Map<String, Object>) data.getValue();
                String firstName;
                String lastName;

                String email;

                String storeName;
                String storeLocation;

                for (Map.Entry<String, Object> entry : accountList.entrySet()){

                    //Get user map
                    Map singleAccount = (Map) entry.getValue();
                    //Get phone field and append to list
                    ManagerDetails manDetails = new ManagerDetails(singleAccount.get("firstName").toString(),
                            singleAccount.get("lastName").toString(), singleAccount.get("email").toString(),
                            singleAccount.get("storeName").toString(), singleAccount.get("storeLocation").toString());
                    accList.add(manDetails);
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                //DO SOME THING WHEN GET DATA FAILED HERE
            }
        });
    }
}