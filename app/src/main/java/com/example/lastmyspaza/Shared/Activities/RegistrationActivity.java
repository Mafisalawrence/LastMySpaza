package com.example.lastmyspaza.Shared.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.Shared.Fragments.Registration.AccountDetails;
import com.example.lastmyspaza.Shared.Fragments.Registration.PersonalDetails;
import com.example.lastmyspaza.Shared.Fragments.Registration.StoreDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RegistrationActivity extends AppCompatActivity implements AccountDetails.OnFragmentInteractionListener,
        PersonalDetails.OnFragmentInteractionListener,
        StoreDetails.OnFragmentInteractionListener{

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
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

       getSupportFragmentManager().beginTransaction()
         .replace(R.id.fragment_container, new AccountDetails(), "one").commit();

    }

}