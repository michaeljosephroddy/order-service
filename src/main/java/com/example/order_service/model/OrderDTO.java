package com.example.order_service.model;

import java.time.LocalDateTime;

public class OrderDTO {
    private int id;
    private LocalDateTime createdAt;
    private int quantity;

    public OrderDTO(int id, LocalDateTime createdAt, int quantity) {
        this.id = id;
        this.createdAt = createdAt;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDTO [toString()=" + super.toString() + "]";
    }

}
