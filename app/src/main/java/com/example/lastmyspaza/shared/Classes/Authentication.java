package com.example.lastmyspaza.shared.Classes;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication {
    private FirebaseAuth mAuth;

    public Authentication(){
        mAuth = FirebaseAuth.getInstance();
    }
    public Task<AuthResult> CreateManagerAccount(String email, String password){

       return mAuth.createUserWithEmailAndPassword(email, password);
    }
    public FirebaseUser GetCurrentUser(){
        return mAuth.getCurrentUser();
    }

    public  void SignOutCurrentUser(){
        mAuth.signOut();
    }

    public Task<AuthResult> SignInUser(String email, String password)
    {
        return mAuth.signInWithEmailAndPassword(email,password);
    }
}
