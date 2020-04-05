package com.model.cart;

import com.model.customer.Customer;
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
    public List<CartItem> getCustomerCart(Customer customer) {
        return getCartDAO().getCartItemsByCustomerId(customer.getAccount());
    }

    @Override
    public boolean addItemToCart(CartItem cartItem) {
        return getCartDAO().addCartItemToCustomerCart(cartItem);
    }

    @Override
    public boolean removeFromCart(CartItem cartItem) {
        return getCartDAO().removeCartItemFromCustomerCart(cartItem);
    }

    @Override
    public boolean checkout(Customer customer) {
        return getCartDAO().checkout(customer);
    }
}
