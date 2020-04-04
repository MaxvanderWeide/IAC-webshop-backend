package com.controller;

import com.model.cart.CartService;
import com.model.cart.CartServices;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopping_cart")
public class CartController {


    @GetMapping("/")
    public String readCookie(@CookieValue(value = "username", defaultValue = "Atta") String username) {
        return "Hey! My username is " + username;
    }

    @GetMapping("/change-username")
    public String setCookie(HttpServletResponse response) {
        // create a cookie
        Cookie cookie = new Cookie("username", "Jovan");

        //add cookie to response
        response.addCookie(cookie);

        return "Username is changed!";
    }
}
