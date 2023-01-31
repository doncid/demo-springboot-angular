package com.apex.demo.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apex.demo.exceptions.OrderNotFoundException;
import com.apex.demo.persistence.entities.Order;
import com.apex.demo.persistence.respositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepo;

    public Order save(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Collection<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepo.findById(orderId);
        if (!order.isPresent())
            throw new OrderNotFoundException("Order not found for id: " + orderId);
        return order.get();
    }

    @Override
    public Order getOrderByIdAndLastName(Long orderId, String lastName) throws OrderNotFoundException {
        Order order = orderRepo.findByIdAndLastName(orderId, lastName);
        if (order == null)
            throw new OrderNotFoundException("Order not found for id: " + orderId + " lastName: " + lastName);
        return order;
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepo.deleteById(orderId);
    }
}
