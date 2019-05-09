package com.example.lastmyspaza.Shared.Models;

import java.util.Date;

public class ProductDetails {

    private String productName;
    private String productCategory;
    private Integer quantity;
    private Double price;
    private String dateAdded;

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