package com.is.ElectroCom.services;

import com.is.ElectroCom.model.Product;
import com.is.ElectroCom.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Clasa de service care implementeaza metodele specificate in interfata pe care aceasta o implementeaza

@Service
public class ProductService implements InterfaceProductService{
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProducts(String type) {
        List<Product> listOfAllProducts = new ArrayList<>();
        List<Product> list = new ArrayList<>();
        productRepository.findAll().iterator().forEachRemaining(listOfAllProducts::add);
        for (Product it: listOfAllProducts) {
            if(it.getType().equals(type))
                list.add(it);
        }
        return list;
    }

    @Override
    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String name) {
        productRepository.deleteById(name);
    }

    @Override
    public void editProduct(String name, String shortDescription, String imageUrl, Double price, Integer qty, Double rating){
        Product product = productRepository.findByName(name);
        product.setShortDescription(shortDescription);
        product.setImageUrl(imageUrl);
        product.setPrice(price);
        product.setQty(qty);
        product.setRating(rating);
        productRepository.save(product);
    }
}