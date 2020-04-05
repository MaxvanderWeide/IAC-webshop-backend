package com.controller;

import com.model.cart.Cart;
import com.model.cart.CartService;
import com.model.cart.CartServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
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
    public ResponseEntity<Object> getShoppingCartWithId(@PathVariable int id) {
        List<Cart> cart = getCartService().getShoppingCartWithId(id);
        if (cart == null) {
            return new ResponseEntity<>("No Cart Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> addProductToCart(@RequestBody Cart cart) {
        Cart cart1 = getCartService().addProductToCart(cart);
        if (cart1 == null) {
            return new ResponseEntity<>("No Cart Found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductFromCart(@RequestBody Cart cart) {
        boolean update = getCartService().updateCart(cart);
        if (!update) {
            return new ResponseEntity<>("Could Not Update", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductFromCart(@PathVariable int id) {
        boolean delete = getCartService().deleteItem(id);
        if (!delete) {
            return new ResponseEntity<>("Could Not Delete", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
