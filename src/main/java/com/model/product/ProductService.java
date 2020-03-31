package com.model.product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    List<Product> getProductsWithinCategory(Category category);
}
