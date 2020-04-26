package com.iacweb.iacweb.controller;

import com.model.Address;
import com.model.customer.Customer;
import com.persistence.customer.CustomerDAO;
import com.persistence.customer.CustomerDAOImpl;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerControllerTest {

    private CustomerDAO customerDAO = new CustomerDAOImpl();

    @Test
    public void testFindUserById()
    {
        Customer customer = customerDAO.getCustomerByID(5);

        Date dateCreated = customer.getCreatedOn();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strCreated = dateFormat.format(dateCreated);

        Assert.assertEquals("Max", customer.getFirstName());
        Assert.assertEquals("van der Weide", customer.getLastName());
        Assert.assertEquals(656764322, customer.getPhone());
        Assert.assertEquals("max.vanderweide@student.hu.nl", customer.getEmail());
        Assert.assertEquals("2020-04-08", strCreated);
    }

    @Test
    public void testFindAdressById()
    {
        Address address = customerDAO.getAddressById(7);

        Assert.assertEquals("Bothheidestraat", address.getStreet());
        Assert.assertEquals("Amersfoort", address.getCity());
        Assert.assertEquals("Utrecht", address.getState());
        Assert.assertEquals("3516 RX", address.getPostalCode());
        Assert.assertEquals("Nederland", address.getCountry());
    }
}
