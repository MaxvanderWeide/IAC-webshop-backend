package com.controller;

import com.model.customer.Customer;
import com.model.customer.CustomerService;
import com.model.customer.CustomerServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    private CustomerService getCustomerService() {
        if (customerService != null) {
            return customerService;
        }
        customerService = new CustomerServices();
        return customerService;
    }

    @GetMapping("/{id}")
    public Map<Object, Object> getCustomerByID(@PathVariable int id) {
        Map<Object, Object> response = new HashMap<>();

        Customer customer = getCustomerService().getCustomerByID(id);
        if (customer == null) {
            response.put(400, "Could not load customer");
            return response;
        }

        response.put(200, customer);
        return response;
    }

}
