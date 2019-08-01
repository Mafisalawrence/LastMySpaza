package com.example.lastmyspaza.Shared.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountDetails implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String storeName;
    private String storeLocation;

    public AccountDetails(String firstName, String lastName, String email, String storeName, String storeLocation){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.storeName = storeName;
        this.storeLocation = storeLocation;
    }
    public AccountDetails(){}

    //private ArrayList<Store> stores;


//    public ArrayList<Store> getStores() {
//        return stores;
//    }
//
//    public void setStores(ArrayList<Store> stores) {
//        this.stores = stores;
//    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreLocation() {
        return storeLocation;
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
