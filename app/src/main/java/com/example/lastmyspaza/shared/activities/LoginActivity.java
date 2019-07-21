package com.example.lastmyspaza.shared.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lastmyspaza.R;
import com.example.lastmyspaza.shared.Models.LoginModel;
import com.example.lastmyspaza.shared.viewModel.LoginViewModel;
import com.example.lastmyspaza.databinding.ActivityLoginBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //UI instances.
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        binding.setLogin(viewModel);

        viewModel.getLoginModel().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                Toast.makeText(LoginActivity.this,loginModel.getEmail(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, com.example.lastmyspaza.shared.activities.MainActivity.class);
        startActivity(intent);
    }
}