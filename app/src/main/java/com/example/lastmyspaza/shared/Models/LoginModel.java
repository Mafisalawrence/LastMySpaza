package com.example.lastmyspaza.shared.Models;

import com.example.lastmyspaza.BR;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

public class LoginModel extends BaseObservable {
    private String email;
    private String password;
    public ObservableField<String> emailError = new ObservableField<>();
    public ObservableField<String> passwordError = new ObservableField<>();

//    public LoginModel(String d,String s)
//    {
//        this.email=d;
//        this.password=s;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        // Notify that the valid property could have changed.
        notifyPropertyChanged(BR.valid);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        // Notify that the valid property could have changed.
        notifyPropertyChanged(BR.valid);
    }

    @Bindable
    public boolean isValid() {
        boolean valid = isEmailValid();
        valid = isPasswordValid(false) && valid;
        return valid;
    }

    public boolean isEmailValid() {
        if (email != null && email.length() > 5) {
            return true;
        } else {
            emailError.set("email error");
            return false;
        }
    }

    public boolean isPasswordValid(boolean setMessage) {
        if (password != null && password.length() > 5) {
            passwordError.set(null);
            return true;
        } else {
                passwordError.set("error");
            return false;
        }
    }
}
