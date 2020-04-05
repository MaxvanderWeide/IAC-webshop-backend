package com.controller;

import com.google.api.client.util.Base64;
import com.google.cloud.storage.Blob;
import com.model.discount.Discount;
import com.model.discount.DiscountService;
import com.model.discount.DiscountServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
            map.put(400, "Discount could not be find");
            return map;
        }

        map.put(200, discount);
        return map;
    }
}
