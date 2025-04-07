package com.example.order_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_service.exception.BadRequestException;
import com.example.order_service.exception.ResourceNotFoundException;
import com.example.order_service.model.Customer;
import com.example.order_service.repository.CustomerRepository;
import com.example.order_service.repository.OrderRepository;

@Service
public class CustomerService {

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
        if (customer == null || customer.getName() == null || customer.getName().isEmpty()) {
            throw new BadRequestException("Customer name cannot be null or empty.");
        }
        return customerRepository.save(customer);
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return The found customer.
     * @throws ResourceNotFoundException if the customer is not found.
     */
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + customerId + " not found."));
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
     * Deletes a customer and all associated orders.
     *
     * @param customerId The ID of the customer to be deleted.
     * @throws ResourceNotFoundException if the customer does not exist.
     */
    public void deleteCustomer(Long customerId) {
        customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cannot delete. Customer with ID " + customerId + " not found."));

        orderRepository.deleteByCustomerId(customerId); // Delete orders first
        customerRepository.deleteById(customerId); // Delete customer
    }

    /**
     * Retrieves a list of customers sorted by creation date.
     *
     * @param sortDirection The sorting direction, either "asc" for ascending or
     *                      "desc" for descending.
     * @return A list of customers sorted by their creation date in the specified
     *         order.
     */
    public List<Customer> getCustomersSorted(String sortDirection) {
        return "asc".equalsIgnoreCase(sortDirection)
                ? customerRepository.findAllByCreatedAtAsc()
                : customerRepository.findAllByCreatedAtDesc();
    }

}
