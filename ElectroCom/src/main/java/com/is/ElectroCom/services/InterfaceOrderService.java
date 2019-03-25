package com.is.ElectroCom.services;

import com.is.ElectroCom.model.Order;

import java.util.List;

// Interfata utilizata pentru specificarea metodelor care necesita implementate pentru a putea realiza functionalitatile dorite

public interface InterfaceOrderService {
    Order findByEmail(String email);
    List<Order> getOrders(String email);
    List<Order> getAllOrders();
    void addNewOrder(Order order);
    void deleteOrder(String email);
    void deleteOrder(Integer id);

}
