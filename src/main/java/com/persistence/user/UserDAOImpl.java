package com.persistence.user;


import com.model.customer.Customer;
import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public Customer getAccountWithEmail(String email) {
        String query = String.format("SELECT * FROM `%s`.customer WHERE email = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return new Customer(
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
