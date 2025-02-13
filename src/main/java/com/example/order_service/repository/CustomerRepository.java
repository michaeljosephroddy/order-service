package com.example.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order_service.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @SuppressWarnings({ "null", "unchecked" })
    Customer save(Customer customer);

    List<Customer> findAll();

}
