package com.apex.demo.services;

import java.util.Collection;

import com.apex.demo.exceptions.OrderNotFoundException;
import com.apex.demo.persistence.entities.Order;

public interface OrderService {
    public Collection<Order> getAllOrders();
    public Order getOrderById(Long orderId) throws OrderNotFoundException;
    public Order getOrderByIdAndLastName(Long orderId, String lastName) throws OrderNotFoundException;
    public void deleteOrderById(Long orderId);
}
