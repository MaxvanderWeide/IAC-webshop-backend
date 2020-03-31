package com.application.product;

import com.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServices implements ProductService {

    @Override
    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            products.add(new Product(10));
        }
        return products;
    }
}
