package com.example.lastmyspaza.Admin.Fragments;

public class Users {
    private String email, userType;

    public Users(){

    }

    public Users(String email, String userType){
        this.email = email;
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {
        return userType;
    }

    public void setUsertype(String userType) {
        this.userType = userType;
    }

}
