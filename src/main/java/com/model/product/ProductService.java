package com.model.product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    Product getProductWithId(int id);
    Product createProduct(Product product);
}
