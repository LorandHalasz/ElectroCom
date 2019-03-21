package com.is.ElectroCom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Clasa care reprezinta modelul Order utilizat de baza de date

@Document
public class Order {
    @Id
    private Integer orderID;
    private String email;
    private String productName;
    private String orderDate;

    public Order(Integer orderID, String email, String productName, String orderDate) {
        this.orderID = orderID;
        this.email = email;
        this.productName = productName;
        this.orderDate = orderDate;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
