package com.is.ElectroCom.repository;

import com.is.ElectroCom.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

// interfata pentru specificarea unor metode utile pentru a realiza anumite operatii asupra bazei de date

@Service
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByEmail(String email);
    void deleteByEmail(String email);
    void deleteByOrderID(Integer orderId);
}
