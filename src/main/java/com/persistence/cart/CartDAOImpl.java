package com.persistence.cart;

import com.model.cart.CartItem;
import com.model.customer.Customer;
import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.*;
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
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeCartItemFromCustomerCart(CartItem cartItem) {
        String createQuery = String.format("DELETE FROM `%s`.customer_products WHERE customer_productsID = ? AND customerID = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createQuery)) {
            preparedStatement.setInt(1, cartItem.getItemID());
            preparedStatement.setInt(2, cartItem.getCustomerID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkout(Customer customer) {
        List<CartItem> cartItems = getCartItemsByCustomerId(customer.getAccount());
        for (CartItem cartItem : cartItems) {
            if (!addToOrderProduct(addToOrder(cartItem), cartItem.getCustomerID())) {
                return false;
            }
        }
        String createQuery = String.format("DELETE FROM `%s`.customer_products WHERE customerID = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createQuery)) {
            preparedStatement.setInt(1, customer.getAccount());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean addToOrderProduct(int orderId, int productId) {
        String safeQuery = String.format("INSERT INTO `%s`.order_products (orderID, productID) VALUES (?, ?)", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(safeQuery)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int addToOrder(CartItem cartItem) {
        String safeQuery = String.format("INSERT INTO `%s`.order(date, customerID, addressID, payment_methodID) VALUES (sysdate(), ?, ?, ?)", ConfigSelector.SCHEMA);
        int id = cartItem.getCustomerID();
        int address = getAddressWithCustomerId(id);
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(safeQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, address);
            preparedStatement.setInt(3, 2);
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getAddressWithCustomerId(int id) {
        String query = String.format("SELECT addressID FROM `%s`.customer WHERE customerID = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
