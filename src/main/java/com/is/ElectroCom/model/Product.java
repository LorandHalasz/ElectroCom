package com.is.ElectroCom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Clasa care reprezinta modelul Product utilizat de baza de date

@Document
public class Product {

    @Id
    private String name;
    private String shortDescription;
    private String imageUrl;
    private Double price;
    private Integer qty;
    private Double rating;
    private String type;

    public Product(String name, String shortDescription, String imageUrl, Double price, Integer qty, Double rating, String type) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.imageUrl = imageUrl;
        this.price = price;
        this.qty = qty;
        this.rating = rating;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
