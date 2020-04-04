package com.persistence.category;

import com.model.product.Product;
import com.persistence.BaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends BaseDAO implements CategoryDAO {

    @Override
    public List<Product> getProductsWithinCategory(int id) {
        List<Product> products = new ArrayList<>();

        String query = "SELECT `productId`, `name` FROM `product` WHERE `CategoryID` = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Product newProduct = new Product().setId(productId).setName(name);
                products.add(newProduct);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
