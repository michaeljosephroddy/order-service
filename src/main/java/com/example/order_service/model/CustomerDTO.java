package com.example.order_service.model;

public class CustomerDTO {
    private String name;
    private String email;
    private int totalOrders;

    public CustomerDTO(String name, String email, int totalOrders) {
        this.name = name;
        this.email = email;
        this.totalOrders = totalOrders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    @Override
    public String toString() {
        return "CustomerDTO [toString()=" + super.toString() + "]";
    }
}
