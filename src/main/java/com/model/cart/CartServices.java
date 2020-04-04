package com.model.cart;

import com.persistence.category.CategoryDAO;
import com.persistence.category.CategoryDAOImpl;

public class CartServices implements CartService {

    private CategoryDAO categoryDAO;

    private CategoryDAO getCategoryDAO() {
        if (categoryDAO != null) {
            return categoryDAO;
        }
        return categoryDAO = new CategoryDAOImpl();
    }
}
