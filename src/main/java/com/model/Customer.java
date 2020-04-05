package com.model;

import java.util.Date;
import java.util.List;

public class Customer extends Account {
    private String name;
    private int phone;
    private String email;
    private List<Order> orders;
    private Address address;

    public Customer(int account, Date createdOn, String name, int phone, String email, List<Order> orders, Address address) {
        super(account, createdOn);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.orders = orders;
        this.address = address;
    }

    //Getters and Setters
    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Phone
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    //Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
