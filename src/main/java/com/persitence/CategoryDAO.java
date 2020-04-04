package com.persitence;

import com.model.category.Category;
import com.model.product.Product;

import java.util.List;

public interface CategoryDAO {

    List<Product> getProductsWithinCategory(Category category);
    List<Product> getProductsWithinCategory(int id);
}
