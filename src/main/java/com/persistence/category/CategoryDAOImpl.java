package com.persistence.category;

import com.model.category.Category;
import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryDAOImpl extends BaseDAO implements CategoryDAO {

    @Override
    public Category getCategoryById(int id) {
        String query = String.format("SELECT * FROM `%s`.category WHERE categoryID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    return new Category(
                            rs.getInt(1),
                            rs.getString(3),
                            rs.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getCategoriesByProductId(int id) {
        List<Category> categories = new ArrayList<>();
        String query = String.format("SELECT c.categoryID, c.description, c.name " +
                "from `%s`.category c " +
                "JOIN `%s`.product_category pc on c.categoryID = pc.categoryID " +
                "WHERE pc.productID = ?", ConfigSelector.SCHEMA, ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Category(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3))
                    );
                }
                return categories;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
