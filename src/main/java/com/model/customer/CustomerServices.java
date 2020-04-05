package com.model.customer;

import com.persistence.customer.CustomerDAO;
import com.persistence.customer.CustomerDAOImpl;


public class CustomerServices implements CustomerService {

    private CustomerDAO customerDAO;

    private CustomerDAO getCustomerDAO() {
        if (customerDAO != null) {
            return customerDAO;
        }
        customerDAO = new CustomerDAOImpl();
        return customerDAO;
    }

    @Override
    public Customer getCustomerByID(int id) {
        return getCustomerDAO().getCustomerByID(id);
    }

    @Override
    public Customer getAccountWithEmail(String email) {
        return getCustomerDAO().getAccountWithEmail(email);
    }
}
