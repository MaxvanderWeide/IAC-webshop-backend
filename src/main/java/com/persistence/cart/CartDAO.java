package com.persistence.cart;

import com.model.cart.CartItem;

import java.util.List;

public interface CartDAO {

    List<CartItem> getCartItemsByCustomerId(int id);
    boolean addCartItemToCustomerCart(CartItem cartItem);
    boolean removeCartItemFromCustomerCart(CartItem cartItem);

//    CartItem getCartWithId(int id);
//
//    boolean checkProductInCart(CartItem cartItem);
//
//    CartItem saveProductToCart(CartItem cartItem);
//
//    boolean updateCart(CartItem cartItem);
//
//    boolean deleteItem(int id);
}
