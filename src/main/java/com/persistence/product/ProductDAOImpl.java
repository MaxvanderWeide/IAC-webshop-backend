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
        String createQuery = String.format("INSERT INTO `%s`.product (name, description, price) VALUE (?, ?, ?);", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                product.setId(rs.getInt(1));
            }
            rs.close();
            List<Integer> ids = new ArrayList<>();
            ids.add(6);
            if (updateProductCategories(product.setCategoryIdList(ids))) {
                return product;
            }
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

    @Override
    public Product updateProduct(Product product) {
        String query = String.format("UPDATE `%s`.product SET `name`=?, `description`=?, `price`=? WHERE productID = ? AND inactive = 0", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
            if (deleteProductCategories(product) && updateProductCategories(product)) {
                return product;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean updateProductCategories(Product product) {
        String insertQuery = String.format("INSERT INTO `%s`.product_category (productID, categoryID) VALUES (?, ?)", ConfigSelector.SCHEMA);
        try (Connection conn = getConnection()) {
            for (int id : product.getCategoryIdList()) {
                try (PreparedStatement stat = conn.prepareStatement(insertQuery)) {
                    stat.setInt(1, product.getId());
                    stat.setInt(2, id);
                    stat.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean deleteProductCategories(Product product) {
        String deleteQuery = String.format("DELETE FROM `%s`.product_category WHERE productID = ?", ConfigSelector.SCHEMA);
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
