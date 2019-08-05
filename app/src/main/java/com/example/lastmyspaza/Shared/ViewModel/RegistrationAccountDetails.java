package com.example.lastmyspaza.Shared.ViewModel;

import android.util.Log;
import android.util.Patterns;
import android.widget.TextView;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Activities.NavigationHelper;
import com.example.lastmyspaza.Shared.Fragments.Registration.StoreDetails;
import com.example.lastmyspaza.Shared.Fragments.Registration.StoreList;
import com.example.lastmyspaza.Shared.Models.AccountDetails;
import com.example.lastmyspaza.Shared.Models.Store;
import com.google.android.material.textfield.TextInputLayout;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

public class RegistrationAccountDetails extends ViewModel {
    private AccountDetails accountDetails = new AccountDetails();
    private Store store;
    private String password;
    private TextInputLayout mEmail ,mPassword, mFirstName, mLastName;
    private TextView managerText, ownerText;

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }
    public void setAccountDetailsPhase1(String email,String password,String role,
                                        String firstName,String lastName){
        accountDetails.setEmail(email);
        accountDetails.setFirstName(firstName);
        accountDetails.setLastName(lastName);
        accountDetails.setRole(role);
        this.password = password;
    }
    public void moveToNextPhase(FragmentManager fragmentManager)
    {
        NavigationHelper navigationHelper = new NavigationHelper(fragmentManager);
        if (isValidInput()){
            navigationHelper.moveTONextFragment(R.id.fragment_container,new StoreList());
        }
    }
    public void setLayoutFields(TextInputLayout mEmail ,TextInputLayout mPassword,
                                TextInputLayout mFirstName,TextInputLayout mLastName,TextView managerText,TextView ownerText)
    {
        this.mEmail = mEmail;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mPassword = mPassword;
        this.managerText = managerText;
        this.ownerText =ownerText;
    }
    public void setErrorFields()
    {
        mLastName.getEditText().setError("required");
        mPassword.getEditText().setError("required");
        mEmail.getEditText().setError("required");
        mFirstName.getEditText().setError("required");
    }
    public Boolean isValidInput()
    {
        if (accountDetails.getLastName().isEmpty() && accountDetails.getFirstName().isEmpty() &&
                accountDetails.getFirstName().isEmpty() && password.isEmpty()){
            setErrorFields();
        }
        if (accountDetails.getEmail().isEmpty() || !isEmailValid()){setError(mEmail,"Invalid email"); return false;}
        else if (accountDetails.getFirstName().isEmpty()){setError(mFirstName,"required");return false;}
        else if (accountDetails.getLastName().isEmpty()){setError(mLastName,"required");return false;}
        else if(password.isEmpty() || !isPasswordValid()){setError(mPassword,"invalid password");return false;}
//        else if (accountDetails.getRole().isEmpty()){return false;} //TODO set error for selection
        else
            return true;
    }
    public void setError(TextInputLayout inputLayout,String error)
    {
        inputLayout.getEditText().setError(error);
    }
    public AccountDetails getAccountDetails() {
        return accountDetails;
    }
    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(accountDetails.getEmail()).matches();
    }

    public boolean isPasswordValid() {
        return password.length() > 5;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setRegistrationDetailsPhase2() {
            accountDetails.setStore(store);
            Log.e("STORE TEST", accountDetails.getStore().getStoreName());

    }
}
