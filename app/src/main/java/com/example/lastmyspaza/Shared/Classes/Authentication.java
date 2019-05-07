package com.example.lastmyspaza.Shared.Classes;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication {

    private Context context;
    private FirebaseAuth mAuth;


    public Authentication(Context _context){
        context = _context;
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
}
