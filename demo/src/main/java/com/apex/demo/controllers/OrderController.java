package com.apex.demo.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apex.demo.exceptions.OrderNotFoundException;
import com.apex.demo.persistence.entities.Order;
import com.apex.demo.services.OrderServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/yearbook")
public class OrderController {

    @Autowired
    private final OrderServiceImpl orderSvc;

    // Get all orders
    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Order>> getAllOrders() {
        Collection<Order> lst = orderSvc.getAllOrders();
        return ResponseEntity.ok(lst);
    }

    // Get order by id
    @GetMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long orderId)
            throws OrderNotFoundException {

        Order order = orderSvc.getOrderById(orderId);
        return ResponseEntity.ok().body(order);
    }

    // Get order by id and last name
    @GetMapping("/order/{id}/{lastname}")
    public ResponseEntity<Order> getOrderByIdAndLastName(@PathVariable("id") Long orderId,
            @PathVariable("lastname") String lastName) throws OrderNotFoundException {

        Order order = orderSvc.getOrderByIdAndLastName(orderId, lastName);
        return ResponseEntity.ok().body(order);
    }

    // Post an order
    @PostMapping("/order")
    public Order createOrder(@RequestBody @Valid Order order) {
        return orderSvc.save(order);
    }

    // Delete an Order
    @DeleteMapping("/order/{id}")
    public Map<String, Boolean> deleteOrderById(@PathVariable(value = "id") Long orderId) throws OrderNotFoundException {
        Order order = orderSvc.getOrderById(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Delete failed. Order not found for orderId: " + orderId);
        }
        orderSvc.deleteOrderById(orderId);
        Map<String, Boolean> resp = new HashMap<>();
        resp.put("deleted", Boolean.TRUE);
        return resp;
    }

}
