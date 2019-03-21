package com.is.ElectroCom.services;

import com.is.ElectroCom.model.Product;

import java.util.List;

// Interfata utilizata pentru specificarea metodelor care necesita implementate pentru a putea realiza functionalitatile dorite

public interface InterfaceProductService {

    Product findByName(String name);
    List<Product> getProducts(String type);
    void addNewProduct(Product product);
    void deleteProduct(String name);
    void editProduct(String name, String shortDescription, String imageUrl, Double price, Integer qty, Double rating);
}
