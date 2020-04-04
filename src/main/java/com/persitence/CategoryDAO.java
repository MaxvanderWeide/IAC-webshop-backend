package com.persitence;

import com.model.product.Product;

import java.util.List;

public interface CategoryDAO {

    List<Product> getProductsWithinCategory(int id);
}
