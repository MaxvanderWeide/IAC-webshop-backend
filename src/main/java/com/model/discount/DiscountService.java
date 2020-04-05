package com.model.discount;

import java.util.HashMap;
import java.util.List;

public interface DiscountService {

    Discount getDiscountWithId(int id);
    HashMap<Object, Object> getAllDiscounts();

}
