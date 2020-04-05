package com.persistence.discount;

import com.model.discount.Discount;
import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DiscountDAOImpl extends BaseDAO implements DiscountDAO {

    @Override
    public Discount getDiscountWithId(int id) {
        Discount discount = null;
        String query = String.format("SELECT * FROM `%s`.discount WHERE discountID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    discount = new Discount(
                            rs.getInt(1),
                            rs.getDate(2),
                            rs.getDate(3),
                            rs.getDouble(4),
                            rs.getInt(5),
                            rs.getString(6)
                    );
                }
            }


            return discount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HashMap<Object, Object> getAllDiscounts() {
        HashMap<Object, Object> map = new HashMap<>();

        String query = String.format("SELECT product.name, product.price, discount.*\n" +
                "FROM `%s`.discount, `%s`.product\n" +
                "WHERE product.productID = discount.productID AND product.inactive = 0\n" +
                "AND sysdate() BETWEEN discount.`from` AND discount.`to`", ConfigSelector.SCHEMA, ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            try(ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    HashMap<String, Object> information = new HashMap<>();
                    information.put("Product price", rs.getDouble(2));
                    information.put("Discount price", rs.getDouble(6));
                    information.put("Discount length", "From " + rs.getDate(4) + " until " + rs.getDate(5));
                    information.put("Advertising text", rs.getString(8));
                    map.put(rs.getString(1), information);
                }
                return map;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
