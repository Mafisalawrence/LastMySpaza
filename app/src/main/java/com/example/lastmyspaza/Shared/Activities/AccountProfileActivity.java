package com.example.lastmyspaza.Shared.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lastmyspaza.Manager.ManagerActivity;
import com.example.lastmyspaza.R;

public class AccountProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, ManagerActivity.class);
        intent.putExtras()
        setContentView(R.layout.activity_account_profile);

    }

}
