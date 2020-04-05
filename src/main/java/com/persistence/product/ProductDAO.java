package com.persistence.product;

import com.model.product.Product;

import java.util.List;

public interface ProductDAO {

    Product getProductWithId(int id);

    Product saveProduct(Product product);

    List<Product> getProductsWithinCategory(int id);
}
