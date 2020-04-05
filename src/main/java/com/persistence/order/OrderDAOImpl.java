package com.persistence.order;

import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl extends BaseDAO implements OrderDAO {

    @Override
    public boolean safeOrder(int id) {
        int orderID = 0;
        int addressID = getAddressID(id);
        ArrayList<Integer> productsList;

        String safeQuery = String.format("INSERT INTO `%s`.order(date, status, customerID, addressID, payment_methodID) VALUES (sysdate(), ?, ?, ?, ?)", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(safeQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, id);
            preparedStatement.setInt(3, addressID);
            preparedStatement.setInt(4, 2);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                orderID = rs.getInt(1);
            }

            productsList = getProductIds(id);
            for (int productID : productsList) {
                safeOrders(orderID, productID);
            }

            deleteShoppingCartCustomer(id);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void safeOrders(int orderID, int productID) {
        String safeQuery = String.format("INSERT INTO `%s`.order_products(orderID, productID) VALUES (?, ?)", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(safeQuery)){
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, productID);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteShoppingCartCustomer(int customerID) {
        String query = String.format("DELETE FROM `%s`.shopping_cart WHERE customerID = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, customerID);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getAddressID(int id) {
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

    public ArrayList<Integer> getProductIds(int customerID) {
        ArrayList<Integer> productIDs = new ArrayList<>();
        String query = String.format("SELECT productID FROM `%s`.shopping_cart WHERE customerID = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, customerID);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    productIDs.add(rs.getInt(1));
                }
                return productIDs;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
