package com.example.lastmyspaza.Shared.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountDetails implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private Store store;

    public AccountDetails(){}

    public void setStore(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
