package com.controller;

import com.model.customer.Customer;
import com.model.customer.CustomerService;
import com.model.customer.CustomerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
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
    public ResponseEntity<Object> getCustomerByID(@PathVariable int id) {
        Customer customer = getCustomerService().getCustomerByID(id);
        if (customer == null) {
            return new ResponseEntity<>("Could Not Load Customer", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
