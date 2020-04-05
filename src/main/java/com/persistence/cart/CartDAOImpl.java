package com.persistence.cart;

import com.model.cart.Cart;
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
    public List<Cart> getCartWithId(int id) {
        List<Cart> cart = new ArrayList<>();
        String query = String.format("SELECT * FROM `%s`.shopping_cart WHERE customerID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    cart.add(new Cart(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4)
                    ));
                }
            }

            return cart;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public boolean checkProductInCart(Cart cart) {
        String checkQuery = String.format("SELECT * FROM `%s`.shopping_cart WHERE customerID = ? AND productID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(checkQuery)) {
            preparedStatement.setInt(1, cart.getCustomerID());
            preparedStatement.setInt(2, cart.getProductID());

            try (ResultSet rs = preparedStatement.executeQuery()) {
                return !rs.next(); // TODO - Look into a better solution to this problem
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Cart saveProductToCart(Cart cart) {
        boolean checkQuery = checkProductInCart(cart);
        if (checkQuery) {
            String createQuery = String.format("INSERT INTO `%s`.shopping_cart (shopping_cartID, productID, amount, customerID) VALUE (?, ?, ?, ?);", ConfigSelector.SCHEMA);

            try (Connection conn = getConnection();
                 PreparedStatement preparedStatement = conn.prepareStatement(createQuery)) {
                preparedStatement.setInt(1, cart.getItemID());
                preparedStatement.setInt(2, cart.getProductID());
                preparedStatement.setInt(3, cart.getAmount());
                preparedStatement.setInt(4, cart.getCustomerID());

                preparedStatement.execute();

                return cart;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            updateCart(cart);
        }
        return cart;
    }

    @Override
    public boolean updateCart(Cart cart) {
        String updateQuery = String.format("UPDATE `%s`.shopping_cart SET amount = ? WHERE productID = ? AND customerID = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, cart.getAmount());
            preparedStatement.setInt(2, cart.getProductID());
            preparedStatement.setInt(3, cart.getCustomerID());

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        String updateQuery = String.format("DELETE FROM `%s`.shopping_cart WHERE shopping_cartID = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
