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
        return new ResponseEntity<>(cartItemList, HttpStatus.OK);
    }

//    @PostMapping()
//    public ResponseEntity<Object> addProductToCart(@RequestBody CartItem cart) {
//        CartItem cart1 = getCartService().addProductToCart(cart);
//        if (cart1 == null) {
//            return new ResponseEntity<>("No CartItem Found", HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(cart, HttpStatus.OK);
//    }
//
//    @PutMapping()
//    public ResponseEntity<Object> updateProductFromCart(@RequestBody CartItem cart) {
//        boolean update = getCartService().updateCart(cart);
//        if (!update) {
//            return new ResponseEntity<>("Could Not Update", HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>("Updated", HttpStatus.OK);
//    }
//
//    @DeleteMapping()
//    public ResponseEntity<Object> deleteProductFromCart() {
//        boolean delete = getCartService().deleteItem(id);
//        if (!delete) {
//            return new ResponseEntity<>("Could Not Delete", HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>("Deleted", HttpStatus.OK);
//    }
//
//    @PostMapping("/checkout")
//    public ResponseEntity<Object> safeOrder() {
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }

}
