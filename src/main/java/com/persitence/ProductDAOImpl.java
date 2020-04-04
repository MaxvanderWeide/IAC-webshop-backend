package com.persitence;

import com.model.product.Product;
import com.service.ConfigSelector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOImpl extends BaseDAO implements ProductDAO {

    @Override
    public Product getProductWithId(int id) {
        ResultSet rs;
        Product product = null;
        String query = String.format("SELECT * FROM `%s`.product WHERE productID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                product = new Product(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getInt(5)
                );
            }

            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveProduct(Product product) {
        String query = String.format("INSERT INTO `%s`.product (name, description, price, categoryID) VALUE (?, ?, ?, ?);", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getCategoryID());

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
