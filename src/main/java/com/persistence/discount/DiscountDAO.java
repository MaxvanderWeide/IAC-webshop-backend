package com.persistence.discount;

import com.model.discount.Discount;

import java.util.HashMap;
import java.util.List;

public interface DiscountDAO {

    Discount getDiscountWithId(int id);
    HashMap<Object, Object> getAllDiscounts();
}
