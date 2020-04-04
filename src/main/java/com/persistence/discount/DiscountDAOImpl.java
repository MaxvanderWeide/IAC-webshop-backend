package com.persistence.discount;

import com.model.discount.Discount;
import com.model.product.Product;
import com.persistence.BaseDAO;
import com.service.ConfigSelector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountDAOImpl extends BaseDAO implements DiscountDAO {

//    @Override
    public Discount getDiscountWithId(int id) {
        ResultSet rs;
        Discount discount = null;
        String query = String.format("SELECT * FROM `%s`.discount WHERE discountID = ?;", ConfigSelector.SCHEMA);

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();
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

            return discount;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
