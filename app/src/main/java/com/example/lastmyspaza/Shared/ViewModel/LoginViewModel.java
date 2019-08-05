package com.example.lastmyspaza.Shared.ViewModel;

import android.util.Log;
import android.util.Patterns;

import com.example.lastmyspaza.Owner.OwnerActivity;
import com.example.lastmyspaza.Shared.Activities.NavigationHelper;
import com.example.lastmyspaza.Shared.Classes.FirebaseQueryLiveData;
import com.example.lastmyspaza.Shared.Models.LoginUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel implements FirebaseAuth.AuthStateListener {

    private LoginUser loginUser;
    private FirebaseAuth firebaseAuth;
    private TextInputLayout email;
    private TextInputLayout password;
    private NavigationHelper navigationHelper;
    private static final DatabaseReference dataRef =  FirebaseDatabase.getInstance().getReference("users");

    public LoginViewModel(LoginUser loginUser, TextInputLayout email,TextInputLayout password,NavigationHelper navigationHelper) {
        this.loginUser = loginUser;
        this.email = email;
        this.password = password;
        this.navigationHelper = navigationHelper;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() != null)
        {
            DatabaseReference d = dataRef.child(firebaseAuth.getCurrentUser().getUid()).child("role");
            FirebaseQueryLiveData mLiveData = new FirebaseQueryLiveData(dataRef);
            Log.e("TEST", mLiveData.toString());
            this.navigationHelper.loadActivity( new OwnerActivity());
        }
    }

    public void tryToLogin() {
        if (isValidInput()) {
            firebaseAuth.signInWithEmailAndPassword(loginUser.getEmail(), loginUser.getPassword())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                email.getEditText().setError( task.getException().getMessage());
                            }
                        }
                    });
        }
    }
    public boolean isValidInput()
    {
        if (!isEmailValid()){
            email.getEditText().setError("Invalid email");
            return false;
        }else if(!isPasswordValid())
        {
            password.getEditText().setError("Invalid password");
            return false;
        }
        return true;
    }
    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(loginUser.getEmail()).matches();
    }

    public boolean isPasswordValid() {
        return loginUser.getPassword().length() > 5;
    }
}