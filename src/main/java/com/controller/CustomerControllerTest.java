package com.controller;

import com.model.customer.Customer;
import com.persistence.customer.CustomerDAO;
import com.persistence.customer.CustomerDAOImpl;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerControllerTest {

    @Test
    public void testFindById()
    {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        Customer customer = customerDAO.getCustomerByID(1);

        Date dateCreated = customer.getCreatedOn();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strCreated = dateFormat.format(dateCreated);

        Assert.assertEquals("Stefan", customer.getFirstName());
        Assert.assertEquals("Jovanovic", customer.getLastName());
        Assert.assertEquals(643493022, customer.getPhone());
        Assert.assertEquals("stefan.jovanovic@student.hu.nl", customer.getEmail());
        Assert.assertEquals("2020-05-08", strCreated);
    }
}
