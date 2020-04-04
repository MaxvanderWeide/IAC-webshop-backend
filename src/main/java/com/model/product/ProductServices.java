package com.model.product;

import com.persitence.ProductDAO;
import com.persitence.ProductDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class ProductServices implements ProductService {

    private ProductDAO productDAO;

    private ProductDAO getIacDao() {
        if (productDAO != null) {
            return productDAO;
        }
        return productDAO = new ProductDAOImpl();
    }

    @Override
    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            products.add(new Product(10, "naam", "beschrijving", 5.00, 1));
        }
        return products;
    }

    @Override
    public Product getProductWithId(int id) { return getIacDao().getProductWithId(id); }

    @Override
    public boolean createProduct(Product product) {
        return getIacDao().saveProduct(product);
    }
}
