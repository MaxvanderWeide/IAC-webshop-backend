package com.controller;

import com.model.order.OrderService;
import com.model.order.OrderServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    private OrderService getOrderService() {
        if (orderService != null) {
            return orderService;
        }
        orderService = new OrderServices();
        return orderService;
    }

    @PostMapping("/{id}")
    public HashMap<Object, Object> safeOrder(@PathVariable int id) {
        HashMap<Object, Object> map = new HashMap<>();

        boolean safe = getOrderService().safeOrder(id);

        if (!safe) {
            map.put(400, "Order failed");
            return map;
        }

        map.put(200, "Order successfully");
        return map;
    }

}
