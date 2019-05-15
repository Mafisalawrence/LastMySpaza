package com.example.lastmyspaza.Shared.Activities;

import android.app.Activity;
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
import com.example.lastmyspaza.Manager.Activities.ManagerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
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

        emailEditText.setText("Admin@spaza.com");
        passwordEditText.setText("123456");

        //Fire base auth instance
        try{
            FirebaseApp.initializeApp(LoginActivity.this);
        }catch (Exception e)
        {
            Log.d("STE",e.toString());
        }

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if(isValidEmail(email)){
                    emailEditText.setError("Invalid email");
                }else if (isPasswordValid(password)){
                    passwordEditText.setError("Invalid password");
                }else
                AttemptUserLogin(email,password);
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
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            String uid = currentUser.getUid();
                            determineUserRole(uid);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public Boolean isValidEmail(String email){
        String emailPattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public Boolean isPasswordValid(String password){
        return password.length() < 6 ;
    }
    public void beginActivity(Activity activity){
            Intent intent = new Intent(LoginActivity.this, activity.getClass());
            startActivity(intent);
    }

    public void determineUserRole(String uid){
        Toast.makeText(LoginActivity.this,uid,Toast.LENGTH_LONG).show();
        DatabaseReference ref = firebaseDatabase.getReference("roles").child(uid).child("roles");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             String role = dataSnapshot.getValue(String.class);
             checkUserRole(role);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
    public void checkUserRole(String role){
        if (role.equals(Roles.Admin.toString())){
            beginActivity(new AdminActivity());
        }else{
            beginActivity(new ManagerActivity());
        }
    }
}
