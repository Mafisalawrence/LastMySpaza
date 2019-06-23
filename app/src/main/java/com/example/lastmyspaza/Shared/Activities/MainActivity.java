package com.example.lastmyspaza.Shared.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkCurrentUser();
    }

    public void loadActivity(Activity activity){
        Intent intent =  new Intent(MainActivity.this,activity.getClass());
        startActivity(intent);
    }

    public void checkCurrentUser() {
        Authentication authentication = new Authentication(MainActivity.this);
        DatabaseIteration databaseIteration = new DatabaseIteration(MainActivity.this);
        FirebaseUser user = authentication.GetCurrentUser();
        setContentView(R.layout.activity_main);
        Button signUp = findViewById(R.id.register);
        Button signIn = findViewById(R.id.login);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(new RegistrationActivity());
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity(new LoginActivity());
            }
        });
//        if (user == null) {
//
//        }
//        else {
//            String currentUserRole = data.getValue(String.class);
//            if (currentUserRole.equals(Roles.Admin.toString())) {
//                loadActivity(new OwnerActivity());
//            } else if (currentUserRole.equals(Roles.Manager.toString())) {
//                loadActivity(new ManagerActivity());
//            }


    }

}