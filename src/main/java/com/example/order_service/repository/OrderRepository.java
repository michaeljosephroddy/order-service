package com.example.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order_service.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @SuppressWarnings({ "null", "unchecked" })
    Order save(Order order);

    List<Order> findAllByCustomerId(int customerId);

    Order findById(int id);

    Order deleteById(int id);

}
