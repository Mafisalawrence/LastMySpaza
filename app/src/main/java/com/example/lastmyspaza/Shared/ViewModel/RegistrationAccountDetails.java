package com.example.lastmyspaza.Shared.ViewModel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmyspaza.Manager.Activities.ManagerActivity;
import com.example.lastmyspaza.Owner.OwnerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Activities.MainActivity;
import com.example.lastmyspaza.Shared.Activities.NavigationHelper;
import com.example.lastmyspaza.Shared.Classes.FirebaseQueryLiveData;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Fragments.Registration.StoreDetails;
import com.example.lastmyspaza.Shared.Fragments.Registration.StoreList;
import com.example.lastmyspaza.Shared.Models.AccountDetails;
import com.example.lastmyspaza.Shared.Models.Store;
import com.google.android.gms.flags.IFlagProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

public class RegistrationAccountDetails extends ViewModel implements FirebaseAuth.AuthStateListener {
    private AccountDetails accountDetails = new AccountDetails();
    private ArrayList<String> storeIdList = new ArrayList<>();
    private Store store;
    private String password;
    private ProgressDialog dialog;
    private NavigationHelper navigationHelper;
    private TextInputLayout mEmail ,mPassword, mFirstName, mLastName;
    private TextView managerText, ownerText;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private static final DatabaseReference dataRef =  FirebaseDatabase.getInstance().getReference("stores");

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
        storeIdList.add(store.getStoreId());
        accountDetails.setStoreId(storeIdList);
    }

    public void createAccount() {
        dialog.show();
        firebaseAuth.addAuthStateListener(this);
        setRegistrationDetailsPhase2();
        firebaseAuth.createUserWithEmailAndPassword(accountDetails.getEmail(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            dialog.cancel();
                           Log.e("Firebase Error", task.getException().toString());
                        }
                    }
                });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        String uid = firebaseAuth.getCurrentUser().getUid();
        if (!uid.isEmpty())
        {
            addRegistrationDetailsToFirebase(uid);
        }
    }

    public void  addRegistrationDetailsToFirebase(final String uid)
    {
        Task<Void> databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(uid).setValue(accountDetails);
        databaseReference.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    addAccountUserToStore(uid);
                }else
                {
                    dialog.cancel();
                    Log.e("Test",task.getException().toString());
                }

            }
        });
    }
    public void addAccountUserToStore(String uid)
    {
        Task<Void> myRef = FirebaseDatabase.getInstance().getReference("stores")
                .child(store.getStoreId() + "/storeManger").setValue(uid);

        myRef.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    if (getAccountDetails().getRole() == Roles.Manager.toString())
                    {
                        navigationHelper.loadActivity(new ManagerActivity());
                        dialog.cancel();
                    }else{
                        navigationHelper.loadActivity(new OwnerActivity());
                        dialog.cancel();
                    }

                }else{
                    Log.e("error", task.getException().toString());
                    dialog.cancel();
                }
            }
        });
    }
    public void InitialiseContext(Activity context){
        this.dialog = new ProgressDialog(context);
        this.navigationHelper = new NavigationHelper(context);
    }
}
