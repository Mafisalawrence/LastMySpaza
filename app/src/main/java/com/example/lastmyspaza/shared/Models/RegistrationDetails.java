package com.example.lastmyspaza.shared.Models;

public class RegistrationDetails {

    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String storeName;
    private String storeLocation;

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
