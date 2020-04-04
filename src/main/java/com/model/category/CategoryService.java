package com.model.category;

import com.model.product.Product;

import java.util.List;

public interface CategoryService {

    List<Product> getProductsWithinCategory(int id);
}
