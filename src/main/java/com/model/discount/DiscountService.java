package com.model.discount;

import java.util.HashMap;

public interface DiscountService {

    Discount getDiscountWithId(int id);

    HashMap<Object, Object> getAllDiscounts();

}
