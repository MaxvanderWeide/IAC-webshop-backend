package com.persitence;

import com.model.product.Product;

import java.sql.Connection;
import java.sql.SQLException;

public class IACDAOImpl extends BaseDAO implements IACDAO{

    @Override
    public boolean saveProduct(Product product) {
        Connection conn = getConnection();
        try {
            conn.createStatement();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
