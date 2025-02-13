package com.example.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.model.Customer;
import com.example.order_service.model.Order;
import com.example.order_service.service.OrderService;

/**
 * Controller class for managing orders and customers.
 * This class provides RESTful endpoints for creating, retrieving, updating,
 * and deleting customers and orders.
 */
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * Creates a new customer.
     *
     * @param customer The customer object to be created.
     * @return The created customer.
     */
    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        return orderService.createCustomer(customer);
    }

    /**
     * Retrieves all customers.
     *
     * @return A list of all customers.
     */
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return orderService.getAllCustomers();
    }

    /**
     * Creates a new order.
     *
     * @param order The order object to be created.
     * @return The created order.
     */
    @PostMapping("/customers/{id}/orders")
    public Order createOrder(@RequestBody Order order, @RequestParam int id) {
        return orderService.createOrder(order, id);
    }

    /**
     * Retrieves all orders for a specific customer.
     *
     * @param id The ID of the customer.
     * @return A list of all orders for the specified customer.
     */
    @GetMapping("/customers/{id}/orders")
    public List<Order> getAllOrders(@PathVariable int id) {
        return orderService.getAllOrders(id);
    }

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id The ID of the order.
     * @return The order with the specified ID.
     */
    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    /**
     * Updates an existing order.
     *
     * @param order The order object containing updated details.
     * @return The updated order.
     */
    @PutMapping("/orders/{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) {
        return orderService.updateOrder(order, id);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to be deleted.
     * @return The deleted order.
     */
    @DeleteMapping("/orders/{id}")
    public Order deleteOrder(@PathVariable int id) {
        return orderService.deleteOrder(id);
    }
}
