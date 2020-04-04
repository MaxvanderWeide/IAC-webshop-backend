package com.persistence.product;

import com.model.product.Product;
import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.*;

public class ProductDAOImpl extends BaseDAO implements ProductDAO {

    @Override
    public Product getProductWithId(int id) {
        ResultSet rs;
        Product product = null;
        String query = String.format("SELECT * FROM `%s`.product WHERE productID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
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
    public Product saveProduct(Product product) {
        String createQuery = String.format("INSERT INTO `%s`.product (name, description, price, categoryID) VALUE (?, ?, ?, ?);", ConfigSelector.SCHEMA);
        int categoryID = getCategoryIDByName("nieuw");

        if (categoryID == 0) {
            return null;
        }

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, categoryID);

            preparedStatement.execute();

            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCategoryIDByName(String name) {
        ResultSet rs;
        String getQuery = String.format("SELECT categoryID FROM `%s`.category WHERE name = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(getQuery)) {
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return createCategoryWithName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int createCategoryWithName(String name) {
        String createQuery = String.format("INSERT INTO `%s`.category (name) VALUE (?);", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
