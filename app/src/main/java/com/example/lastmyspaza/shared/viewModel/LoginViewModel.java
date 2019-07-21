package com.example.lastmyspaza.shared.viewModel;

import android.view.View;

import com.example.lastmyspaza.shared.Classes.Authentication;
import com.example.lastmyspaza.shared.Models.LoginModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
       // private Authentication authentication =  new Authentication();
        private View.OnFocusChangeListener onFocusEmail;
        private View.OnFocusChangeListener onFocusPassword;
        private LoginModel loginModel = new LoginModel();
        private MutableLiveData<LoginModel> loginModelMutableLiveData = new MutableLiveData<>();
        public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
        public MutableLiveData<String> Password = new MutableLiveData<>();

        public View.OnFocusChangeListener getEmailOnFocusChangeListener() {
        return onFocusEmail;
        }

        public View.OnFocusChangeListener getPasswordOnFocusChangeListener() {
        return onFocusPassword;
        }

        public MutableLiveData<LoginModel> getLoginModel(){
            return loginModelMutableLiveData;
        }
        public void onClick(View view) {
                loginModelMutableLiveData.setValue(loginModel);
        }
}
