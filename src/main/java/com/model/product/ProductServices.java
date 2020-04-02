package com.model.product;

import com.persitence.IACDAO;
import com.persitence.IACDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductServices implements ProductService {

    private IACDAO iacdao;

    private IACDAO getIacDao() {
        if (iacdao != null) {
            return iacdao;
        }
        return iacdao = new IACDAOImpl();
    }

    @Override
    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            products.add(new Product(10, "naam", 5.00));
        }
        return products;
    }

    @Override
    public List<Product> getProductsWithinCategory(Category category) {
        return null;
    }

    @Override
    public boolean createProduct(Product product) {
        return getIacDao().saveProduct(product);
    }
}
