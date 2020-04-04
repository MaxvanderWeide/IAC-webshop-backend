package com.model.product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    boolean createProduct(Product product);
}
