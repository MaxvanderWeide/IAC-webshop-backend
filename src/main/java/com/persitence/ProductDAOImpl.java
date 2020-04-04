package com.persitence;

import com.model.product.Product;
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
    public boolean saveProduct(Product product) {
        String createQuery = String.format("INSERT INTO `%s`.product (name, description, price, categoryID) VALUE (?, ?, ?, ?);", ConfigSelector.SCHEMA);
        int categoryID = getNewCategoryID();

        if (categoryID == 0) {
            return false;
        }

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createQuery)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, categoryID);

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Gets the ID from category named 'nieuw'
    public int getNewCategoryID() {
        ResultSet rs;
        String getQuery = String.format("SELECT categoryID FROM `%s`.category WHERE name = 'nieuw'", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(getQuery)) {
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return createCategoryNew();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int createCategoryNew() {
        String createQuery = String.format("INSERT INTO `%s`.category (name, description) VALUE ('nieuw', 'categorie van nieuwe aangemaakte producten');", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
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
