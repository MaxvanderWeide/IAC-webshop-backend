package com.persistence.customer;

import com.model.Address;
import com.model.customer.Customer;
import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO implements CustomerDAO {


    @Override
    public Customer getCustomerByID(int id) {
        String query = String.format("SELECT * FROM `%s`.customer WHERE customerID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return new Customer(
                            rs.getInt(1),
                            rs.getDate(7),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            getAddressById(rs.getInt(6))
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Address getAddressById(int id) {
        Address address = null;
        String query = String.format("SELECT street, city, state, postalCode, country FROM `%s`.address WHERE addressID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try(ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    address = new Address(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)
                            );
                }
            }

            return address;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Customer getAccountWithEmail(String email) {
        String query = String.format("SELECT * FROM `%s`.customer WHERE email = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return new Customer(
                            rs.getInt(1),
                            rs.getDate(7),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5),
                            null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
