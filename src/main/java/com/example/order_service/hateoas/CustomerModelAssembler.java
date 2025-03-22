package com.example.order_service.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.order_service.controller.CustomerController;
import com.example.order_service.controller.OrderController;
import com.example.order_service.model.Customer;
import com.example.order_service.model.CustomerDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<CustomerDTO>> {

        @Override
        public EntityModel<CustomerDTO> toModel(Customer customer) {
                CustomerDTO dto = new CustomerDTO(
                                customer.getId(),
                                customer.getName(),
                                customer.getEmail(),
                                customer.getTotalOrders());

                return EntityModel.of(dto,
                                linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                                                .getCustomerById(customer.getId()))
                                                .withSelfRel(),
                                linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).getAllOrders(customer.getId(),
                                                0, 10))
                                                .withRel("customer-orders"),
                                linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).createCustomer(customer))
                                                .withRel("update-customer"),
                                linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                                                .deleteCustomer(customer.getId()))
                                                .withRel("delete-customer"));
        }
}
