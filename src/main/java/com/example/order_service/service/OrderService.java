package com.example.order_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.order_service.exception.BadRequestException;
import com.example.order_service.exception.ResourceNotFoundException;
import com.example.order_service.model.Customer;
import com.example.order_service.model.Order;
import com.example.order_service.repository.CustomerRepository;
import com.example.order_service.repository.OrderRepository;

/**
 * Service class for managing business logic related to orders.
 * This class acts as an intermediary between the controller and repository
 * layers.
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    /**
     * Creates a new order.
     *
     * @param order The order object to be created.
     * @return The created order.
     * @throws BadRequestException if the order object is null or invalid.
     */
    public Order createOrder(Order order) {
        if (order == null) {
            throw new BadRequestException("Order cannot be null");
        }
        if (order.getProduct() == null || order.getProduct().isEmpty()) {
            throw new BadRequestException("Product cannot be null or empty");
        }
        if (order.getQuantity() == null || order.getQuantity() <= 0) {
            throw new BadRequestException("Quantity must be greater than 0");
        }

        // Validate customer existence
        customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return orderRepository.save(order);
    }

    /**
     * Retrieves all orders for a specific customer.
     *
     * @param id The ID of the customer.
     * @return A list of all orders for the specified customer.
     */
    public Page<Order> getAllOrders(Long customerId, Pageable pageable) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cannot get all orders. Customer with ID " + customerId + " not found."));
        return orderRepository.findByCustomerId(customerId, pageable);
    }

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id The ID of the order.
     * @return The order with the specified ID.
     * @throws ResourceNotFoundException if the order is not found.
     */
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    /**
     * Updates an existing order.
     *
     * @param order The order object containing updated details.
     * @return The updated order.
     * @throws ResourceNotFoundException if the order does not exist.
     * @throws BadRequestException       if the order object is null or invalid.
     */
    public Order updateOrder(Order order) {
        if (order == null || order.getId() == null) {
            throw new BadRequestException("Invalid order: order and ID must not be null");
        }

        orderRepository.findById(order.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cannot update: Order not found with id: " + order.getId()));

        return orderRepository.save(order);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to be deleted.
     * @throws ResourceNotFoundException if the order does not exist.
     */
    public void deleteOrder(Long id) {
        orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete: Order not found with id: " + id));

        orderRepository.deleteById(id);
    }

    /**
     * Deletes all orders associated with a given customer.
     *
     * @param customerId The ID of the customer whose orders should be deleted.
     */
    public void deleteAllOrders(Long customerId) {
        orderRepository.deleteByCustomerId(customerId); // Delete orders first
    }

    /**
     * Retrieves a list of orders within a specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of orders created within the specified date range.
     */
    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByCreatedAtBetween(startDate, endDate);
    }

    /**
     * Retrieves a list of orders sorted by creation date.
     *
     * @param sortDirection The sorting direction, either "asc" for ascending or
     *                      "desc" for descending.
     * @return A list of orders sorted by their creation date in the specified
     *         order.
     */
    public List<Order> getOrdersSorted(String sortDirection) {
        return "asc".equalsIgnoreCase(sortDirection)
                ? orderRepository.findAllByCreatedAtAsc()
                : orderRepository.findAllByCreatedAtDesc();
    }
}
