package com.model.cart;

import com.persistence.cart.CartDAO;
import com.persistence.cart.CartDAOImpl;

public class CartServices implements CartService {

    private CartDAO cartDAO;

    private CartDAO getCartDAO() {
        if (cartDAO != null) {
            return cartDAO;
        }
        return cartDAO = new CartDAOImpl();
    }


}
