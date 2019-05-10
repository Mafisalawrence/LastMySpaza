package com.example.lastmyspaza.Shared.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lastmyspaza.Admin.AdminActivity;
import com.example.lastmyspaza.Manager.ManagerActivity;
import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Classes.Authentication;
import com.example.lastmyspaza.Shared.Classes.DatabaseIteration;
import com.example.lastmyspaza.Shared.Enums.Roles;
import com.example.lastmyspaza.Shared.Interfaces.OnGetDataListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

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

        if (user == null) {
            setContentView(R.layout.activity_main);
            Button signUp = findViewById(R.id.signUp);
            Button signIn = findViewById(R.id.signIn);

          signIn.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    loadActivity(new LoginActivity());
        }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    loadActivity(new RegistrationActivity());
        }
        });
        }
        else {
            databaseIteration.getCurrentUserRole(user.getUid(), new OnGetDataListener() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(DataSnapshot data) {
                    Toast.makeText(MainActivity.this, data.getValue(String.class), Toast.LENGTH_LONG).show();
                    String currentUserRole = data.getValue(String.class);
                   if (currentUserRole.equals(Roles.Admin.toString())) {
                    loadActivity(new AdminActivity());
                    } else if (currentUserRole.equals(Roles.Manager.toString())) {
                    loadActivity(new ManagerActivity());
                    }
                }
                @Override
                public void onFailed(DatabaseError databaseError) {

                }
            });
        }
    }

}