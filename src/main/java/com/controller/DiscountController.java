package com.controller;

import com.model.discount.Discount;
import com.model.discount.DiscountService;
import com.model.discount.DiscountServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discount")
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
    public Map<Object, Object> getDiscountWithId(@PathVariable int id) {
        HashMap<Object, Object> map = new HashMap<>();
        Discount discount = getDiscountService().getDiscountWithId(id);

        if (discount == null) {
            map.put(400, "Discount could not be found");
            return map;
        }

        map.put(200, discount);
        return map;
    }

    @GetMapping("/all")
    public Map<Object, Object> getAllDiscounts() {
        HashMap<Object, Object> map = new HashMap<>();
        HashMap<Object, Object> discounts = getDiscountService().getAllDiscounts();

        if (discounts == null) {
            map.put(400, "Discounts could not be found");
            return map;
        }

        map.put(200, discounts);
        return map;
    }
}
