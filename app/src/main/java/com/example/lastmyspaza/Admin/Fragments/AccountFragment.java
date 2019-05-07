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
import android.widget.TextView;

import com.example.lastmyspaza.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private List<Users> userList = new ArrayList<>();
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        userAdapter = new UsersAdapter(userList);
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
        Users user = new Users("Mad Max: Fury Road", "Action & Adventure");
        userList.add(user);

        user = new Users("Inside Out", "Animation, Kids & Family");
        userList.add(user);

        user = new Users("Star Wars: Episode VII - The Force Awakens", "Action");
        userList.add(user);

        user = new Users("Shaun the Sheep", "Animation");
        userList.add(user);

        user = new Users("The Martian", "Science Fiction & Fantasy");
        userList.add(user);

        user = new Users("Mission: Impossible Rogue Nation", "Action");
        userList.add(user);

        user = new Users("Up", "Animation");
        userList.add(user);

        user = new Users("Star Trek", "Science Fiction");
        userList.add(user);

        user = new Users("The LEGO Movie", "Animation");
        userList.add(user);

        user = new Users("Iron Man", "Action & Adventure");
        userList.add(user);

        user = new Users("Aliens", "Science Fiction");
        userList.add(user);

        user = new Users("Chicken Run", "Animation");
        userList.add(user);

        user = new Users("Back to the Future", "Science Fiction");
        userList.add(user);

        user = new Users("Raiders of the Lost Ark", "Action & Adventure");
        userList.add(user);

        user = new Users("Goldfinger", "Action & Adventure");
        userList.add(user);

        user = new Users("Guardians of the Galaxy", "Science Fiction & Fantasy");
        userList.add(user);

        userAdapter.notifyDataSetChanged();
    }
}
