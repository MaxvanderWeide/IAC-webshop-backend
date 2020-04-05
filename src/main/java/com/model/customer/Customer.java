package com.model.customer;

import com.model.Account;
import com.model.Address;

import java.util.Date;

public class Customer extends Account {
    private String firstName;
    private String lastName;
    private int phone;
    private String email;
    private Address address;

    public Customer(int id, Date createdOn, String firstName, String lastName, int phone, String email, Address address) {
        super(id, createdOn);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
}
