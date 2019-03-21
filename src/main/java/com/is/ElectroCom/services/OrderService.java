package com.is.ElectroCom.services;

import com.is.ElectroCom.model.Order;
import com.is.ElectroCom.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Clasa de service care implementeaza metodele specificate in interfata pe care aceasta o implementeaza

@Service
public class OrderService implements InterfaceOrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findByEmail(String email) {
        return orderRepository.findByEmail(email);
    }

    @Override
    public List<Order> getOrders(String email) {
        List<Order> listOfAllOrders = new ArrayList<>();
        List<Order> list = new ArrayList<>();
        orderRepository.findAll().iterator().forEachRemaining(listOfAllOrders::add);
        for (Order it: listOfAllOrders) {
            if(it.getEmail().equals(email))
                list.add(it);
        }
        return list;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        orderRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String email) {
        orderRepository.deleteByEmail(email);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteByOrderID(orderId);
    }
}
