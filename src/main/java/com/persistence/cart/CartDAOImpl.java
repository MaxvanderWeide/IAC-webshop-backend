package com.persistence.cart;

import com.model.cart.CartItem;
import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartDAOImpl extends BaseDAO implements CartDAO {

    @Override
    public List<CartItem> getCartItemsByCustomerId(int id) {
        List<CartItem> cartItem = new ArrayList<>();
        String query = String.format("SELECT * FROM `%s`.customer_products WHERE customerID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    cartItem.add(new CartItem(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(4),
                            rs.getInt(3)
                    ));
                }
                return cartItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean addCartItemToCustomerCart(CartItem cartItem) {
        String createQuery = String.format("INSERT INTO `%s`.customer_products (productID, amount, customerID) VALUE (?, ?, ?);", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createQuery)) {
            preparedStatement.setInt(1, cartItem.getProductID());
            preparedStatement.setInt(2, cartItem.getAmount());
            preparedStatement.setInt(3, cartItem.getCustomerID());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//    private ProductDAO productDAO;
//
//    private ProductDAO getIacDao() {
//        if (productDAO != null) {
//            return productDAO;
//        }
//        productDAO = new ProductDAOImpl();
//        return productDAO;
//    }

//    @Override
//    public CartItem getCartWithId(int id) {
//        List<CartItem> cartItem = new ArrayList<>();
//        String query = String.format("SELECT * FROM `%s`.shopping_cart WHERE customerID = ?;", ConfigSelector.SCHEMA);
//
//        try (Connection conn = getConnection();
//             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                while (rs.next()) {
//                    cartItem.add(new CartItem(
//                            rs.getInt(1),
//                            rs.getInt(2),
//                            rs.getInt(3),
//                            rs.getInt(4)
//                    ));
//                }
//            }
//
//            return cartItem;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return Collections.emptyList();
//    }
//
//    @Override
//    public boolean checkProductInCart(CartItem cartItem) {
//        String checkQuery = String.format("SELECT * FROM `%s`.shopping_cart WHERE customerID = ? AND productID = ?;", ConfigSelector.SCHEMA);
//
//        try (Connection conn = getConnection();
//             PreparedStatement preparedStatement = conn.prepareStatement(checkQuery)) {
//            preparedStatement.setInt(1, cartItem.getCustomerID());
//            preparedStatement.setInt(2, cartItem.getProductID());
//
//            try (ResultSet rs = preparedStatement.executeQuery()) {
//                return !rs.next(); // TODO - Look into a better solution to this problem
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    @Override
//    public CartItem saveProductToCart(CartItem cartItem) {
//        boolean checkQuery = checkProductInCart(cartItem);
//        boolean checkActive = getIacDao().checkProductStatus(cartItem.getProductID());
//        if (checkQuery && checkActive) {
//            String createQuery = String.format("INSERT INTO `%s`.shopping_cart (productID, amount, customerID) VALUE (?, ?, ?);", ConfigSelector.SCHEMA);
//
//            try (Connection conn = getConnection();
//                 PreparedStatement preparedStatement = conn.prepareStatement(createQuery)) {
//                preparedStatement.setInt(1, cartItem.getProductID());
//                preparedStatement.setInt(2, cartItem.getAmount());
//                preparedStatement.setInt(3, cartItem.getCustomerID());
//
//                preparedStatement.execute();
//
//                return cartItem;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return null;
//        } else if (checkActive){
//            updateCart(cartItem);
//            return cartItem;
//        }
//        return null;
//    }
//
//    @Override
//    public boolean updateCart(CartItem cartItem) {
//        String updateQuery = String.format("UPDATE `%s`.shopping_cart SET amount = ? WHERE productID = ? AND customerID = ?", ConfigSelector.SCHEMA);
//
//        try (Connection conn = getConnection();
//             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
//            preparedStatement.setInt(1, cartItem.getAmount());
//            preparedStatement.setInt(2, cartItem.getProductID());
//            preparedStatement.setInt(3, cartItem.getCustomerID());
//
//            preparedStatement.execute();
//
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean deleteItem(int id) {
//        String updateQuery = String.format("DELETE FROM `%s`.shopping_cart WHERE shopping_cartID = ?", ConfigSelector.SCHEMA);
//
//        try (Connection conn = getConnection();
//             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
//            preparedStatement.setInt(1, id);
//
//            preparedStatement.execute();
//
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
}
