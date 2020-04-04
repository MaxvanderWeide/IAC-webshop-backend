package com.persistence.cart;

import com.model.cart.Cart;
import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl extends BaseDAO implements CartDAO {
    @Override
    public List<Cart> getCartWithId(int id) {
        ResultSet rs;
        List<Cart> cart = new ArrayList<>();
        String query = String.format("SELECT * FROM `%s`.shopping_cart WHERE customerID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                cart.add(new Cart(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)
                ));
            }

            return cart;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Cart saveProductToCart(Cart cart) {
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
    }
}
