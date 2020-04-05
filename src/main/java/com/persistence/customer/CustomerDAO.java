package com.persistence.customer;

import com.model.Address;
import com.model.customer.Customer;


public interface CustomerDAO {

    Customer getCustomerByID(int id);

    Address getAddressById(int id);

    Customer getAccountWithEmail(String email);

}
