package com.example.lastmyspaza.Shared.Fragments.Registration;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import static android.support.constraint.Constraints.TAG;

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
        mSignUp = view.findViewById(R.id.button_submit);

        authentication = new Authentication(getContext());
        databaseIteration = new DatabaseIteration(getContext());
        managerDetails = (ManagerDetails) getArguments().getSerializable("managerDetails");

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managerDetails.setStoreName(mStoreName.getText().toString());
                managerDetails.setStoreLocation(mStoreLocation.getText().toString());
                addManagerDetailsToDb(getArguments().getString("identifier"));
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
