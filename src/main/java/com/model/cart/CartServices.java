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

//    @Override
//    public List<CartItem> getShoppingCartsWithId(int id) {
//        return getCartDAO().getCartsWithId(id);
//    }
//
//    @Override
//    public boolean updateCart(CartItem cart) {
//        return getCartDAO().updateCart(cart);
//    }
//
//    @Override
//    public CartItem addProductToCart(CartItem cart) {
//        return getCartDAO().saveProductToCart(cart);
//    }
//
//    @Override
//    public boolean deleteItem(int id) {
//        return getCartDAO().deleteItem(id);
//    }
}
