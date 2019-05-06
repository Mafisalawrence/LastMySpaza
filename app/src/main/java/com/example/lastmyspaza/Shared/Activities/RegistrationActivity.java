package com.example.lastmyspaza.Shared.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Fragments.RegistrationFragments.PersonalDetails;

class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);

       getSupportFragmentManager().beginTransaction()
         .replace(R.id.fragment_container, new PersonalDetails()).commit();

    }

}