package com.controller;

import com.model.cart.CartItem;
import com.model.cart.CartService;
import com.model.cart.CartServices;
import com.model.customer.CustomerService;
import com.model.customer.CustomerServices;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private CartService cartService;
    private CustomerService customerService;

    private CartService getCartService() {
        if (cartService != null) {
            return cartService;
        }
        cartService = new CartServices();
        return cartService;
    }

    private CustomerService getCustomerService() {
        if (customerService != null) {
            return customerService;
        }
        customerService = new CustomerServices();
        return customerService;
    }

    @GetMapping()
    public ResponseEntity<Object> getCustomerCart(HttpServletRequest request) {
        Claims claims = AuthController.decodeJWT(request.getHeader("authorization"));
        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<CartItem> cartItemList = getCartService().getCustomerCart(getCustomerService().getAccountWithEmail(claims.get("username").toString()));
        if (cartItemList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cartItemList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> addProductToCard(HttpServletRequest request, @RequestBody CartItem cartItem) {
        Claims claims = AuthController.decodeJWT(request.getHeader("authorization"));
        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!getCartService().addItemToCart(cartItem.setCustomerID(getCustomerService().getAccountWithEmail(claims.get("username").toString()).getAccount()))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteProductFromCart(HttpServletRequest request, @RequestBody CartItem cartItem) {
        Claims claims = AuthController.decodeJWT(request.getHeader("authorization"));
        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!getCartService().removeFromCart(cartItem.setCustomerID(getCustomerService().getAccountWithEmail(claims.get("username").toString()).getAccount()))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Object> checkout(HttpServletRequest request) {
        Claims claims = AuthController.decodeJWT(request.getHeader("authorization"));
        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!getCartService().checkout(getCustomerService().getAccountWithEmail(claims.get("username").toString()))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
