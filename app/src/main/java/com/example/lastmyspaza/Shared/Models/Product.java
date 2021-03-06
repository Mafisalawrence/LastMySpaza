package com.example.lastmyspaza.Shared.Models;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {

    private String id;
    private String productName;
    private String productCategory;
    private Integer quantity;
    private Double price;
    private String dateAdded;
    private String storeID;

    public Product(){

    }
    public Product(String productName,String productCategory, Integer quantity, Double price , String dateAdded){
        this.productName = productName;
        this.price = price;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.productCategory = productCategory;
    }

    public String getStoreID() {
        return storeID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
