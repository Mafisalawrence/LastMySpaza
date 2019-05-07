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
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;


public class PersonalDetails extends Fragment {
    private OnFragmentInteractionListener mListener;
    private EditText mFirstName;
    private EditText mLastName;
    private Button mContinue;

    public PersonalDetails() {
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
        View view = inflater.inflate(R.layout.fragment_personal_details, container, false);

        mFirstName = view.findViewById(R.id.first_name);
        mLastName = view.findViewById(R.id.last_name);
        mContinue = view.findViewById(R.id.button_continue);

        final String firstName = mFirstName.getText().toString();
        final String lastName = mLastName.getText().toString();
        final StoreDetails storeDetails = new StoreDetails();
        final ManagerDetails managerDetails = (ManagerDetails) getArguments().getSerializable("managerDetails");

        Toast.makeText(getContext(),managerDetails.getEmail(),Toast.LENGTH_LONG).show();
        mContinue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    managerDetails.setFirstName(firstName);
                    managerDetails.setLastName(lastName);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("managerDetails",managerDetails);
                    bundle.putString("identifier", getArguments().getString("identifier"));
                    storeDetails.setArguments(bundle);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, storeDetails)
                            .commit();
                }

        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
