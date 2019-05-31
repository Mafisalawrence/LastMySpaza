package com.example.lastmyspaza.Shared.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastmyspaza.Owner.OwnerActivity;
import com.example.lastmyspaza.Manager.Activities.ManagerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.example.lastmyspaza.Shared.Models.ManagerDetails;
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


    private Authentication authentication;
    private DatabaseIteration databaseIteration;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private TextView linkSignUp;
    private String uid;
    private String role;
    private String storeID;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //UI instances
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        linkSignUp = findViewById(R.id.link_signup);
        signInButton = findViewById(R.id.email_sign_in_button);

        emailEditText.setText("test@owner.com");
        passwordEditText.setText("12345678");

        //Fire base auth instance
        try{
            FirebaseApp.initializeApp(LoginActivity.this);
        }catch (Exception e)
        {
            Log.d("STE",e.toString());
        }

        databaseIteration = new DatabaseIteration(this);
        authentication =  new Authentication(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void AttemptUserLogin(String email, String password)
    {
        authentication.SignInUser(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                             uid = task.getResult().getUser().getUid();
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
        databaseIteration.getCurrentUserRole(uid, new OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                role = data.getValue(String.class);
                getManagerStore();
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.d("User-role",databaseError.toString());
            }
        });
    }
    public void checkUserRole(String role){
        if (role.equals(Roles.Admin.toString())){
            beginActivity(new OwnerActivity());
        }else{
            beginActivity(new ManagerActivity());
        }
    }
    public void getManagerStore(){
        databaseIteration.getManagerStore(uid, new OnGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(DataSnapshot data) {
                for (DataSnapshot dataSnapshot: data.getChildren())
                {
                    storeID = dataSnapshot.getKey();
                }
                storeDetailsInSharedPreference();
                checkUserRole(role);
            }

            @Override
            public void onFailed(DatabaseError databaseError) {
                Log.d("Manager store",databaseError.toString());
            }
        });
    }
    public void storeDetailsInSharedPreference(){
        SharedPreferences sharedPreferences =  getApplicationContext().getSharedPreferences("manager",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("managerUid",uid);
        editor.putString("managerStore",storeID);
        editor.commit();
    }
}
