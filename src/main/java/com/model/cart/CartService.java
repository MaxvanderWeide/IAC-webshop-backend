package com.model.cart;

import java.util.List;

public interface CartService {

    Cart addProductToCart(Cart cart);

    List<Cart> getShoppingCartWithId(int id);

    boolean updateCart(Cart cart);

    boolean deleteItem(int id);

}
