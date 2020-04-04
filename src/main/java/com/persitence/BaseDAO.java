package com.persitence;

import com.service.ConfigSelector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {
    private Connection connection;

    protected Connection getConnection() {
        try {
            if (connection == null || connection.isClosed() || !connection.isValid(10)) {

                connection =
                        DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s?useSSL=false",
                                ConfigSelector.HOST, ConfigSelector.PORT,
                                ConfigSelector.SCHEMA), ConfigSelector.USERNAME,
                                ConfigSelector.PASSWORD);

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        }

        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
