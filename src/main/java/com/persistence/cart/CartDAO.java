package com.persistence.cart;

import com.model.cart.Cart;

import java.util.List;

public interface CartDAO {

    List<Cart> getCartWithId(int id);
    boolean checkProductInCart(Cart cart);
    Cart saveProductToCart(Cart cart);
    boolean updateCart(Cart cart);
    boolean deleteItem(int id);
}
