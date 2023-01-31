package com.apex.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.apex.demo.persistence.entities.Order;
import com.apex.demo.persistence.respositories.OrderRepository;
import com.apex.demo.services.OrderServiceImpl;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderServiceImpl orderSvc;

    private Order createTestOrder(String firstName, String lastName, String grade, String address1,
            String address2, String city, String state, String zip) {
        return Order.builder()
                .firstName(firstName)
                .lastName(lastName)
                .grade("12")
                .address1(address1)
                .address2(address2)
                .city(city)
                .state(state)
                .zip(zip)
                .build();
    }

    @BeforeEach
    public void before() {
        orderRepo.deleteAll();
    }

    @Test
    void shouldSaveOrder() throws Exception {
        Order order = createTestOrder("herman", "munster", "12",
                "1313 Mockingbird Lane", null, "Mockingbird Heights", "CA", "131313");
        Order savedOrder = orderSvc.save(order);

        Order loadedOrder = orderSvc.getOrderById(savedOrder.getId());

        Assertions.assertNotNull(loadedOrder);
        Assertions.assertNotNull(loadedOrder.getId());
    }

    @Test
    void shouldFindOrderByIdAndLastName() throws Exception {
        Order order = createTestOrder("herman", "munster", "12",
                "1313 Mockingbird Lane", null, "Mockingbird Heights", "CA", "131313");
        Order savedOrder = orderSvc.save(order);

        Order loadedOrder = orderSvc.getOrderByIdAndLastName(savedOrder.getId(), "munster");

        Assertions.assertNotNull(loadedOrder);
        Assertions.assertNotNull(loadedOrder.getId());
    }

}
