package com.persistence.product;

import com.model.product.Product;
import com.persistence.BaseDAO;
import com.persistence.category.CategoryDAOImpl;
import com.service.ConfigSelector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDAOImpl extends BaseDAO implements ProductDAO {

    @Override
    public Product getProductWithId(int id) {
        Product product = null;
        String query = String.format("SELECT * FROM `%s`.product WHERE productID = ? AND inactive = 0;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    product = new Product(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            new CategoryDAOImpl().getCategoriesByProductId(rs.getInt(1))
                    );
                }
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
        ResultSet rs;

        if (categoryID == 0) {
            return null;
        }

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, categoryID);

            preparedStatement.execute();

            rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                product.setId(rs.getInt(1));
            }

            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteProductById(int id) {
        String query = String.format("UPDATE `%s`.product SET `inactive`=1 WHERE productID = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCategoryIDByName(String name) {
        // TODO - Move this to CategoryDAO
        String getQuery = String.format("SELECT categoryID FROM `%s`.category WHERE name = ?", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(getQuery)) {
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
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

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Product> getProductsWithinCategory(int id) {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * from product p " +
                "JOIN product_category pc " +
                "on p.productID = pc.productID " +
                "WHERE pc.categoryID = ? AND p.inactive = 0";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new Product(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            new CategoryDAOImpl().getCategoriesByProductId(resultSet.getInt(1))
                    ));
                }
                return products;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM `product` WHERE inactive = 0";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new Product(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            new CategoryDAOImpl().getCategoriesByProductId(resultSet.getInt(1))));
                }
                return products;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
