package com.is.ElectroCom.repository;

import com.is.ElectroCom.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

// interfata pentru specificarea unor metode utile pentru a realiza anumite operatii asupra bazei de date

@Service
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String name);
}
