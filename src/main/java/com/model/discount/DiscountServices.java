package com.model.discount;

import com.persistence.discount.DiscountDAO;
import com.persistence.discount.DiscountDAOImpl;

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
}
