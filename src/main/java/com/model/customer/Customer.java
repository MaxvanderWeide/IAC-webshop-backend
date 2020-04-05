package com.model.customer;

import com.model.Account;
import com.model.Address;
import com.model.Order;

import java.util.Date;
import java.util.List;

public class Customer extends Account {
    private String firstName;
    private String lastName;
    private int phone;
    private String email;
    private List<Order> orders;
    private Address address;

    public Customer(Date createdOn, String firstName, String lastName, int phone, String email, Address address) {
        super(createdOn);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Customer(Date createdOn, String firstName, String lastName, int phone, String email, List<Order> orders, Address address) {
        super(createdOn);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.orders = orders;
        this.address = address;
    }

    //Getters and Setters
    //First name
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    //Last name
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

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

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
