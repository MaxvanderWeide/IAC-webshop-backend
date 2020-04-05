package com.model.cart;

import com.persistence.cart.CartDAO;
import com.persistence.cart.CartDAOImpl;

import java.util.List;

public class CartServices implements CartService {

    private CartDAO cartDAO;

    private CartDAO getCartDAO() {
        if (cartDAO != null) {
            return cartDAO;
        }
        cartDAO = new CartDAOImpl();
        return cartDAO;
    }

    @Override
    public List<Cart> getShoppingCartWithId(int id) {
        return getCartDAO().getCartWithId(id);
    }

    @Override
    public boolean updateCart(Cart cart) {
        return getCartDAO().updateCart(cart);
    }

    @Override
    public Cart addProductToCart(Cart cart) {
        return getCartDAO().saveProductToCart(cart);
    }

    @Override
    public boolean deleteItem(int id) {
        return getCartDAO().deleteItem(id);
    }
}
