package com.persistence.user;

import com.model.customer.Customer;

public interface UserDAO {

    Customer getAccountWithEmail(String email);
}
