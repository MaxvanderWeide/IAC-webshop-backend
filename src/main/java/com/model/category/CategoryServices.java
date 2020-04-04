package com.model.category;

import com.model.product.Product;
import com.persitence.CategoryDAO;
import com.persitence.CategoryDAOImpl;

import java.util.List;

public class CategoryServices implements CategoryService {

    private CategoryDAO categoryDAO;

    private CategoryDAO getCategoryDAO() {
        if (categoryDAO != null) {
            return categoryDAO;
        }
        return categoryDAO = new CategoryDAOImpl();
    }

    @Override
    public List<Product> getProductsWithinCategory(Category category) {
        return getCategoryDAO().getProductsWithinCategory(category);
    }

    @Override
    public List<Product> getProductsWithinCategory(int id) {
        return getCategoryDAO().getProductsWithinCategory(id);
    }
}
