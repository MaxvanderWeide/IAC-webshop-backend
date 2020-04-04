package com.model.cart;

import com.persitence.category.CategoryDAO;
import com.persitence.category.CategoryDAOImpl;

public class CartServices implements CartService {

    private CategoryDAO categoryDAO;

    private CategoryDAO getCategoryDAO() {
        if (categoryDAO != null) {
            return categoryDAO;
        }
        return categoryDAO = new CategoryDAOImpl();
    }
}
