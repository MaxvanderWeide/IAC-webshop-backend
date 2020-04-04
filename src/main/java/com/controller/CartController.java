package com.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.model.cart.CartService;
import com.model.cart.CartServices;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
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

    @PostMapping("/change-username")
    public String setCookie(HttpServletResponse response, @RequestBody String name) {
        // create a cookie
        Cookie cookie = new Cookie("username", "Hallo");

        //add cookie to response
        response.addCookie(cookie);

        return "Username is changed!";
    }

    @PostMapping("/change")
    public String setName(@RequestParam("naam") String naam, HttpServletResponse response) {
        System.out.printf(naam);

        response.addCookie(new Cookie("username", naam));

        return String.format("Username is now " + naam);

    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<TomcatServletWebServerFactory>() {
            @Override
            public void customize(TomcatServletWebServerFactory factory) {
                TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) factory;
                tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));
            }
        };
    }
}
