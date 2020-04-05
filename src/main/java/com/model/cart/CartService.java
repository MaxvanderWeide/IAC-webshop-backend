package com.model.cart;

import com.model.customer.Customer;

import java.util.List;

public interface CartService {

//    CartItem addProductToCart(CartItem cart);
//
//    List<CartItem> getShoppingCartsWithId(int id);
//
//    boolean updateCart(CartItem cart);
//
//    boolean deleteItem(int id);

    List<CartItem> getCustomerCart(Customer customer);

}
