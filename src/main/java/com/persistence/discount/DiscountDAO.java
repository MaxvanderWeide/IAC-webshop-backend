package com.persistence.discount;

import com.model.discount.Discount;

public interface DiscountDAO {

    Discount getDiscountWithId(int id);
}
