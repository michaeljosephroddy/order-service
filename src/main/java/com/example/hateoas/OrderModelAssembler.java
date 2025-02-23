package com.example.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.order_service.controller.CustomerController;
import com.example.order_service.controller.OrderController;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<OrderDTO>> {

    @Override
    public EntityModel<OrderDTO> toModel(Order order) {
        OrderDTO dto = new OrderDTO(order.getId(), order.getCreatedAt(), order.getQuantity());

        return EntityModel.of(dto,
                linkTo(methodOn(OrderController.class).getOrder(order.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).getCustomerById(order.getCustomerId())).withRel("customer"),
                linkTo(methodOn(OrderController.class).updateOrder(order)).withRel("update-order"));
    }
}
