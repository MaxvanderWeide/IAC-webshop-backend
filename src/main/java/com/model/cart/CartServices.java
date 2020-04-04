package com.model.cart;

import com.persitence.cart.CartDAO;
import com.persitence.cart.CartDAOImpl;
import com.persitence.category.CategoryDAO;
import com.persitence.category.CategoryDAOImpl;

public class CartServices implements CartService {

    private CartDAO cartDAO;

    private CartDAO getCartDAO() {
        if (cartDAO != null) {
            return cartDAO;
        }
        return cartDAO = new CartDAOImpl();
    }


}
