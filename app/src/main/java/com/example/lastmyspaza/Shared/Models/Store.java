package com.example.lastmyspaza.Shared.Models;

import java.io.Serializable;

public class Store implements Serializable {

    private String storeId;
    private String storeName;
    private String storeLocation;
    private String storeManagerId;
    private String storeOwnerId;

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
        return storeManagerId;
    }

    public void setStoreManager(String storeManager) {
        this.storeManagerId = storeManager;
    }

    public String getStoreOwner() {
        return storeOwnerId;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwnerId = storeOwner;
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
