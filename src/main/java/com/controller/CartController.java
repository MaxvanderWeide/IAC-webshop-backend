package com.controller;

import com.model.cart.Cart;
import com.model.cart.CartService;
import com.model.cart.CartServices;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
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

    private CartService cartService;

    private CartService getCartService() {
        if (cartService != null) {
            return cartService;
        }
        cartService = new CartServices();
        return cartService;
    }


    @GetMapping("/{id}")
    public Map<Object, Object> getShoppingCartWithId(@PathVariable int id) {
        Map<Object, Object> response = new HashMap<>();

        List<Cart> cart = getCartService().getShoppingCartWithId(id);
        if (cart == null) {
            response.put(400, "Could not load shopping cart data");
            return response;
        }

        response.put(200, cart);
        return response;
    }

    @PostMapping()
    public Map<Object, Object> addProductToCart(@RequestBody Cart cart) {
        Map<Object, Object> response = new HashMap<>();
        Cart cart1 = getCartService().addProductToCart(cart);

        if (cart1 == null) {
            response.put(400, "Product could not get added");
            return response;
        }
        response.put(200, cart);
        return response;
    }

    @PutMapping()
    public Map<Object, Object> updateProductFromCart(@RequestBody Cart cart) {
        Map<Object, Object> response = new HashMap<>();

        boolean update = getCartService().updateCart(cart);

        if (!update) {
            response.put(400, "Shopping cart failed to be updated");
            return response;
        }

        response.put(200, "Shopping cart updated");
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<Object, Object> deleteProductFromCart(@PathVariable int id) {
        Map<Object, Object> response = new HashMap<>();

        boolean delete = getCartService().deleteItem(id);
        if (!delete) {
            response.put(400, "Could not delete item");
            return response;
        }

        response.put(200, "Item deleted");
        return response;
    }


    // Down below is the first try to use cookies to safe the shopping_cart, there are alot of problems with this idea.

    @GetMapping("/cookies")
    public String readCookie(@CookieValue(value = "shopping_cart", defaultValue = "Atta") String username) {
        return "Hey! My username is " + username;
    }

    @PostMapping("/change")
    public String setName(@RequestParam("productID") int productID, @RequestParam("amount") int amount, HttpServletResponse response) {
        HashMap<String, Integer> product = new HashMap<>();
        product.put("Product ID", productID);
        product.put("Amount", amount);

        response.addCookie(new Cookie("shopping_cart", "informatie"));

        return "Iets gebeurt";
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            TomcatServletWebServerFactory tomcat = factory;
            tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));
        };
    }
}
