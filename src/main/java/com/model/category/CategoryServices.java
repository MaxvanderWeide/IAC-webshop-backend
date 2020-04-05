package com.model.category;

import com.model.product.Product;
import com.persistence.category.CategoryDAO;
import com.persistence.category.CategoryDAOImpl;

import java.util.List;

public class CategoryServices implements CategoryService {

    private CategoryDAO categoryDAO;

    private CategoryDAO getCategoryDAO() {
        if (categoryDAO != null) {
            return categoryDAO;
        }
        categoryDAO = new CategoryDAOImpl();
        return categoryDAO;
    }

    @Override
    public List<Product> getProductsWithinCategory(int id) {
        return getCategoryDAO().getProductsWithinCategory(id);
    }
}
