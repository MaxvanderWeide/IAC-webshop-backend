package com.persistence.product;

import com.model.product.Product;

public interface ProductDAO {

    Product getProductWithId(int id);
    Product saveProduct(Product product);
}
