package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.example.lastmyspaza.Shared.ViewModel.RegistrationAccountDetails;
import com.google.android.material.textfield.TextInputLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AccountDetails extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private TextInputLayout mFirstName;
    private TextInputLayout mLastName;

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

        final ImageView managerSelection= view.findViewById(R.id.manager_imageView);
        ImageView ownerSelection =  view.findViewById(R.id.owner_imageView);
        final TextView managerText = view.findViewById(R.id.manager_text);
        final TextView ownerText = view.findViewById(R.id.owner_text);

        Button mContinue = view.findViewById(R.id.button_continue);

        managerDetails = new ManagerDetails();

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDetails.setEmail(mEmail.getEditText().getText().toString());
                managerDetails.setFirstName(mFirstName.getEditText().getText().toString());
                managerDetails.setLastName(mLastName.getEditText().getText().toString());

                setAccountDetails();
                moveTONextFragment();

            }
        });


        managerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDetails.setRole(Roles.Manager.toString());
                managerText.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        ownerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDetails.setRole(Roles.Admin.toString());
                ownerText.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        return view;
    }

    private void setAccountDetails(){
       // registrationAccountDetails = ViewModelProviders.of(getActivity()).get(RegistrationAccountDetails.class);
        registrationAccountDetails.setManagerDetails(managerDetails);
        registrationAccountDetails.setPassword(mPassword.getEditText().getText().toString());
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
