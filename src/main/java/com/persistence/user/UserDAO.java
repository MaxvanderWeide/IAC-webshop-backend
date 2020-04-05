package com.persistence.user;

import com.model.Customer;

public interface UserDAO {

    Customer getAccountWithEmail(String email);
}
