package com.persitence;

import com.model.category.Category;
import com.model.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends BaseDAO implements CategoryDAO {

    @Override
    public List<Product> getProductsWithinCategory(Category category) {

        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM product WHERE CategoryID = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, category.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String description = resultSet.getString(4);
                Product newProduct = new Product(id, name, description, price, category.getId());
                products.add(newProduct);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getProductsWithinCategory(int id) {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM product WHERE CategoryID = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                double price = resultSet.getDouble(4);
                Product newProduct = new Product(productId, name, description, price, id);
                products.add(newProduct);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
