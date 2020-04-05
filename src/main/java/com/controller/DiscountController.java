package com.controller;

import com.model.discount.Discount;
import com.model.discount.DiscountService;
import com.model.discount.DiscountServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private DiscountService discountService;

    private DiscountService getDiscountService() {
        if (discountService != null) {
            return discountService;
        }
        discountService = new DiscountServices();
        return discountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDiscountWithId(@PathVariable int id) {
        Discount discount = getDiscountService().getDiscountWithId(id);
        if (discount == null) {
            return new ResponseEntity<>("Discount Not Found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllDiscounts() {
        HashMap<Object, Object> discounts = getDiscountService().getAllDiscounts();
        if (discounts == null) {
            return new ResponseEntity<>("No Discounts Found Found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }
}
