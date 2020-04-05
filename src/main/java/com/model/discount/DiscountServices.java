package com.model.discount;

import com.persistence.discount.DiscountDAO;
import com.persistence.discount.DiscountDAOImpl;

import java.util.HashMap;
import java.util.List;

public class DiscountServices implements DiscountService {

    private DiscountDAO discountDAO;

    private DiscountDAO getDiscountDAO() {
        if (discountDAO != null) {
            return discountDAO;
        }
        discountDAO = new DiscountDAOImpl();
        return discountDAO;
    }

    @Override
    public Discount getDiscountWithId(int id) {
        return getDiscountDAO().getDiscountWithId(id);
    }

    @Override
    public HashMap<Object, Object> getAllDiscounts() {
        return getDiscountDAO().getAllDiscounts();
    }
}
