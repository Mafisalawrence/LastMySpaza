package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmyspaza.Admin.Fragments.AccountFragment;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.ViewModel.RegistrationAccountDetails;
import com.example.lastmyspaza.Shared.ViewModel.StoreListViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class AccountDetails extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mFirstName;
    private EditText mLastName;
    private RegistrationAccountDetails registrationAccountDetails;
    private ManagerDetails managerDetails;
    public AccountDetails() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_details, container, false);

        mEmail = view.findViewById(R.id.email);
        mPassword = view.findViewById(R.id.password);
        mFirstName = view.findViewById(R.id.first_name);
        mLastName = view.findViewById(R.id.last_name);
        mPassword = view.findViewById(R.id.password);

        ImageView managerSelection= view.findViewById(R.id.manager_imageView);
        ImageView ownerSelection =  view.findViewById(R.id.owner_imageView);
        final TextView managerText = view.findViewById(R.id.manager_text);
        final TextView ownerText = view.findViewById(R.id.owner_text);

        Button mCancel = view.findViewById(R.id.left_button);
        Button mContinue = view.findViewById(R.id.right_button);
        mCancel.setText("cancel");
        mContinue.setText("continue");

        managerDetails = new ManagerDetails();

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDetails.setEmail(mEmail.getText().toString());
                managerDetails.setFirstName(mFirstName.getText().toString());
                managerDetails.setLastName(mLastName.getText().toString());

                setAccountDetails();
                moveTONextFragment();

            }
        });


        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        managerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDetails.setRole(Roles.Manager.toString());
                managerText.setTextColor(getResources().getColor(R.color.app_color_primary));
            }
        });
        ownerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDetails.setRole(Roles.Admin.toString());
                ownerText.setTextColor(getResources().getColor(R.color.app_color_primary));
            }
        });

        return view;
    }

    private void setAccountDetails(){
        registrationAccountDetails = ViewModelProviders.of(getActivity()).get(RegistrationAccountDetails.class);
        registrationAccountDetails.setManagerDetails(managerDetails);
        registrationAccountDetails.setPassword(mPassword.getText().toString());
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

    public void moveTONextFragment(){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if(managerDetails.getRole().equals(Roles.Admin.toString())){
            fragmentTransaction.replace(R.id.fragment_container, new StoreList() , "storeList")
           .commit();
        }else{
            fragmentTransaction.replace(R.id.fragment_container, new StoreDetails() )
            .commit();
        }
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
