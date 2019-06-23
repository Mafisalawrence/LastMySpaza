package com.example.lastmyspaza.Shared.Models;

import java.io.Serializable;

public class Store implements Serializable {

    private String storeId;
    private String storeName;
    private String storeLocation;
    private String storeManager;
    private String storeOwner;

    public String getStoreLocation() {
        return storeLocation;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public String getStoreOwner() {
        return storeOwner;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Store(String storeName,String storeLocation){
        setStoreName(storeName);
        setStoreLocation(storeLocation);
    }
    //TODO only needed for testing
    public Store()
    {

    }
}
