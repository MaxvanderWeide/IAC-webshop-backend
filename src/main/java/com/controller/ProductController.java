package com.controller;

import com.model.category.CategoryService;
import com.model.category.CategoryServices;
import com.model.product.Product;
import com.model.product.ProductService;
import com.model.product.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    private ProductService getProductService() {
        if (productService != null) {
            return productService;
        }
        return productService = new ProductServices();
    }

    private CategoryService getCategoryService() {
        if (categoryService != null) {
            return categoryService;
        }
        return categoryService = new CategoryServices();
    }

    @GetMapping("/products")
    public List<String> getProducts() {
        List<String> products = new ArrayList<>();
        products.add("Product");
        products.add("Product");
        products.add("Product");
        return products;
    }

    @GetMapping("/products/{Id}")
    public String getProductWithId(@PathVariable int id) {
        return String.format("Product: %s", id);
    }

    @GetMapping("/products/{Id}/categories")
    public String getCategoriesWithProductId(@PathVariable int id) {
        return String.format("Category: %s", id);
    }

    @PostMapping("/products")
    public HttpStatus createProduct(@RequestBody Product product) {
        return getProductService().createProduct(product) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }


    @GetMapping("/categories")
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Category");
        categories.add("Category");
        categories.add("Category");
        return categories;
    }

    @GetMapping("/categories/{id}")
    public List<Product> getCategoriesWithId(@PathVariable int id) {
        return getCategoryService().getProductsWithinCategory(id);
    }
}
