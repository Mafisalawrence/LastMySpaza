package com.example.lastmyspaza.Shared.Models;

public class LoginUser {

    public String email;
    public String password;

    public LoginUser(String email, String password)
    {
        this.email = email;
        this.password = password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
