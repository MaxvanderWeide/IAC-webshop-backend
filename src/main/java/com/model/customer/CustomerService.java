package com.model.customer;


public interface CustomerService {
    Customer getCustomerByID(int id);
    Customer getAccountWithEmail(String email);
}
