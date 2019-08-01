package com.example.lastmyspaza.Shared.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.lastmyspaza.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private NavigationHelper navigationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationHelper = new NavigationHelper(this);
        checkCurrentUser();

    }

    public void checkCurrentUser() {
        setContentView(R.layout.activity_main);
        Button signUp = findViewById(R.id.register);
        Button signIn = findViewById(R.id.login);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationHelper.loadActivity(new RegistrationActivity());
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationHelper.loadActivity(new LoginActivity());
            }
        });

    }

}