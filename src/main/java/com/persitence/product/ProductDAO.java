package com.persitence.product;

import com.model.product.Product;

public interface ProductDAO {

    Product getProductWithId(int id);
    boolean saveProduct(Product product);
}
