package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.content.Context;
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

import com.example.lastmyspaza.Admin.Fragments.AccountFragment;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class AccountDetails extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mContinue;
    private Authentication authentication;
    private ManagerDetails managerDetails;
    private DatabaseIteration databaseIteration;

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
       // mContinue = view.findViewById(R.id.button_continue);

        managerDetails = new ManagerDetails();
        authentication = new Authentication(getContext());
        databaseIteration = new DatabaseIteration(getContext());

//        mContinue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                managerDetails.setEmail(mEmail.getText().toString());
//
//                authentication.CreateManagerAccount(managerDetails.getEmail(),mPassword.getText().toString())
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if ( task.isSuccessful()){
//                                    String userId = authentication.GetCurrentUser().getUid();
//                                    addManagerRole(userId);
//                                }else
//                                {
//                                    Toast.makeText(getContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        }); }
//                });

        return view;
    }
    public void addManagerRole(final String userID)
    {
        databaseIteration.addRoleToDB(Roles.Manager.toString(),userID)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            moveTONextFragment(userID);
                        }else
                        {
                            Toast.makeText(getContext(),task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
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

    public void moveTONextFragment(String uid){
        PersonalDetails personalDetails =  new PersonalDetails();
        Bundle bundle =  new Bundle();
        bundle.putString("identifier",uid);
        bundle.putSerializable("managerDetails", managerDetails);
        personalDetails.setArguments(bundle);
        getFragmentManager().beginTransaction()
        .replace(R.id.fragment_container, personalDetails )
        .commit();
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
