package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.ViewModel.RegistrationAccountDetails;
import com.google.android.material.textfield.TextInputLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class AccountDetails extends Fragment {

    private TextInputLayout mEmail ,mPassword, mFirstName, mLastName;
    private ImageView managerSelection, ownerSelection;
    private TextView managerText, ownerText;
    private Button mContinue;
    private String email,password,role,firstName,lastName;
    private RegistrationAccountDetails registrationAccountDetails;

    public AccountDetails() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_details, container, false);
        InitialiseComponents(view);

        registrationAccountDetails = ViewModelProviders.of(getActivity()).get(RegistrationAccountDetails.class);

        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAccountDetails();
                Toast.makeText(getContext(),email,Toast.LENGTH_LONG).show();
                registrationAccountDetails.setAccountDetailsPhase1(email,password,role,firstName,lastName);
                registrationAccountDetails.setLayoutFields(mEmail,mPassword,mFirstName,mLastName,managerText,ownerText);
                registrationAccountDetails.moveToNextPhase(getFragmentManager());
        }
        });
        managerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = Roles.Manager.toString();
                managerText.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        ownerSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = Roles.Admin.toString();
                ownerText.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        return view;
    }

    private void setAccountDetails(){
        email = mEmail.getEditText().getText().toString();
        firstName = mFirstName.getEditText().getText().toString();
        lastName = mLastName.getEditText().getText().toString();
        password = mPassword.getEditText().getText().toString();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void InitialiseComponents(View view){
        mEmail = view.findViewById(R.id.email);
        mPassword = view.findViewById(R.id.password);
        mFirstName = view.findViewById(R.id.first_name);
        mLastName = view.findViewById(R.id.last_name);
        mPassword = view.findViewById(R.id.password);
        managerSelection= view.findViewById(R.id.manager_imageView);
        ownerSelection =  view.findViewById(R.id.owner_imageView);
        managerText = view.findViewById(R.id.manager_text);
        ownerText = view.findViewById(R.id.owner_text);
        mContinue = view.findViewById(R.id.button_continue);

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
