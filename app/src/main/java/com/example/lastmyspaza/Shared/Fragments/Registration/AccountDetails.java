package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lastmyspaza.Admin.Fragments.AccountFragment;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;

public class AccountDetails extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mContinue;

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
        mConfirmPassword = view.findViewById(R.id.confirm_password);
        mContinue = view.findViewById(R.id.button_continue);

        final ManagerDetails managerDetails = new ManagerDetails();


        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonalDetails personalDetails =  new PersonalDetails();
                managerDetails.setEmail(mEmail.getText().toString());
                managerDetails.setPassword(mPassword.getText().toString());

                Bundle bundle =  new Bundle();
                bundle.putSerializable("managerDetails", managerDetails);
                personalDetails.setArguments(bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, personalDetails )
                        .commit();
            }
        });

        return view;
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
}
