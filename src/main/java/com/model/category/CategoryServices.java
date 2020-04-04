package com.model.category;

import com.model.product.Product;
import com.persitence.ProductDAO;
import com.persitence.ProductDAOImpl;

import java.util.List;

public class CategoryServices implements CategoryService {

    private ProductDAO productDAO;

    private ProductDAO getIacDao() {
        if (productDAO != null) {
            return productDAO;
        }
        return productDAO = new ProductDAOImpl();
    }

    @Override
    public List<Product> getProductsWithinCategory(Category category) {
        return ;
    }
}
