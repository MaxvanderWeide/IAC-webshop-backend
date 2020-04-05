package com.persistence.cart;

import com.model.cart.CartItem;
import com.model.customer.Customer;

import java.util.List;

public interface CartDAO {

    List<CartItem> getCartItemsByCustomerId(int id);
    boolean addCartItemToCustomerCart(CartItem cartItem);
    boolean removeCartItemFromCustomerCart(CartItem cartItem);
    boolean checkout(Customer customer);
}
