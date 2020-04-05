package com.model.cart;

import com.model.customer.Customer;

import java.util.List;

public interface CartService {

    List<CartItem> getCustomerCart(Customer customer);

    boolean addItemToCart(CartItem cartItem);

    boolean removeFromCart(CartItem cartItem);

    boolean checkout(Customer customer);

}
