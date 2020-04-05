package com.persistence.product;

import com.model.product.Product;

import java.util.List;

public interface ProductDAO {

    Product getProductWithId(int id);

    Product saveProduct(Product product);

    boolean deleteProductById(int id);

    Product updateProduct(Product product);

    List<Product> getProductsWithinCategory(int id);

    List<Product> getAllProducts();

    boolean checkProductStatus(int productID);
}
