package com.persistence.discount;

import com.model.discount.Discount;

import java.util.HashMap;

public interface DiscountDAO {

    Discount getDiscountWithId(int id);

    HashMap<Object, Object> getAllDiscounts();
}
