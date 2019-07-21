package com.example.lastmyspaza.shared.viewModel;

import android.util.Log;
import android.view.View;

import com.example.lastmyspaza.shared.Models.RegistrationDetails;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrationViewModel extends ViewModel {
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    public MutableLiveData<String> FirstName = new MutableLiveData<>();
    public MutableLiveData<String> LastName = new MutableLiveData<>();
    public MutableLiveData<String> Role = new MutableLiveData<>();
    public MutableLiveData<String> StoreName = new MutableLiveData<>();
    public MutableLiveData<String> StoreLocation = new MutableLiveData<>();
    private MutableLiveData<RegistrationDetails> registrationDetails = new MutableLiveData<>();


    public MutableLiveData<RegistrationDetails> getRegistrationDetails() {
        return registrationDetails;
    }

    public void onClick(View view) {
        Log.d("Click", "My Button was clicked");
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
