package com.example.order_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_service.model.Customer;
import com.example.order_service.model.Order;
import com.example.order_service.repository.CustomerRepository;
import com.example.order_service.repository.OrderRepository;

/**
 * Service class for managing business logic related to customers and orders.
 * This class acts as an intermediary between the controller and repository
 * layers.
 */
@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    /**
     * Creates a new customer.
     *
     * @param customer The customer object to be created.
     * @return The created customer.
     */
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return A list of all customers.
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Creates a new order.
     *
     * @param order The order object to be created.
     * @return The created order.
     */
    public Order createOrder(Order order, int id) {
        order.setCustomerId(id);
        return orderRepository.save(order);
    }

    /**
     * Retrieves all orders for a specific customer.
     *
     * @param id The ID of the customer.
     * @return A list of all orders for the specified customer.
     */
    public List<Order> getAllOrders(int id) {
        return orderRepository.findAllByCustomerId(id);
    }

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id The ID of the order.
     * @return The order with the specified ID.
     */
    public Order getOrder(int id) {
        return orderRepository.findById(id);
    }

    /**
     * Updates an existing order.
     *
     * @param order The order object containing updated details.
     * @return The updated order.
     */
    public Order updateOrder(Order order, int id) {
        order.setId(id);
        return orderRepository.save(order);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to be deleted.
     * @return The deleted order.
     */
    public Order deleteOrder(int id) {
        return orderRepository.deleteById(id);
    }
}
