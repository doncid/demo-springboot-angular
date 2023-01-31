package com.apex.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.apex.demo.controllers.OrderController;
import com.apex.demo.persistence.entities.Order;
import com.apex.demo.services.OrderServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@WebMvcTest(OrderController.class)
@RequiredArgsConstructor
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    OrderController orderController;

    @MockBean
    OrderServiceImpl orderSvc;

    private Order order;

    @BeforeEach
    public void before() {
        order = Order.builder().firstName("herman")
                .lastName("munster")
                .grade("12")
                .address1("1313 Mockingbird Lane")
                .city("Mockingbird Heights")
                .state("CA")
                .zip("131313")
                .build();
    }

    @SneakyThrows
    @Test
    void shouldCallOrderController() {
        mockMvc.perform(
                get("/api/yearbook/order")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void shouldGetOrderById() throws Exception {

        Mockito.when(orderSvc.getOrderById(1L)).thenReturn(order);

        mockMvc.perform(
                get("/api/yearbook/order/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("herman"));
    }

    @SneakyThrows
    @Test
    void shouldGetOrderByIdAndLastName() throws Exception {

        Mockito.when(orderSvc.getOrderByIdAndLastName(1L, "munster")).thenReturn(order);

        mockMvc.perform(
                get("/api/yearbook/order/1/munster")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("herman"));
    }

}
