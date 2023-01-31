package com.apex.demo.persistence.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apex.demo.persistence.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByIdAndLastName(Long id, String lastName);
}
