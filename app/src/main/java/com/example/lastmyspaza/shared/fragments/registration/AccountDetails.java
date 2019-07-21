package com.example.lastmyspaza.shared.fragments.registration;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.shared.viewModel.RegistrationViewModel;
import com.example.lastmyspaza.databinding.FragmentAccountDetailsBinding;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class AccountDetails extends Fragment {

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
        RegistrationViewModel viewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);
        FragmentAccountDetailsBinding binding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_account_details,container,false);
        binding.setLifecycleOwner(this);
        binding.setRegistration(viewModel);
        return binding.getRoot();
    }

//    private void setAccountDetails(){
//        registrationViewModel = ViewModelProviders.of(getActivity()).get(RegistrationViewModel.class);
//        registrationViewModel.setRegistrationDetails(registrationDetails);
//        registrationViewModel.setPassword(mPassword.getEditText().getText().toString());
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
           // mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
      //  mListener = null;
    }

//    public void moveTONextFragment(){
//
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        if(registrationDetails.getRole().equals(Roles.Admin.toString())){
//            fragmentTransaction.replace(R.id.fragment_container, new StoreList() , "storeList")
//           .commit();
//        }else{
//            fragmentTransaction.replace(R.id.fragment_container, new StoreDetails() )
//            .commit();
//        }
//        }
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
