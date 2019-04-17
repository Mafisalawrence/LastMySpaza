package com.example.lastmyspaza.Shared;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastmyspaza.Admin.AdminActivity;
import com.example.lastmyspaza.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //UI instances
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        Button signInButton = findViewById(R.id.email_sign_in_button);

        emailEditText.setText("admin@lastspaza.com");
        passwordEditText.setText("lastSpazaAdmin");
        //Firebase auth instance
        try{
            FirebaseApp.initializeApp(LoginActivity.this);
        }catch (Exception e)
        {
            Log.d("STE",e.toString());
        }


        mAuth = FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttemptUserLogin(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    private void AttemptUserLogin(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this,"lOGIN SUCCESS",Toast.LENGTH_LONG).show();
                            Intent adminActivity = new Intent(LoginActivity.this, AdminActivity.class);
                            startActivity(adminActivity);
                          //  FirebaseUser user = auth.getCurrentUser(); //gets current user
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }

    private void CreateUser(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser(); //gets current user
                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }
}
