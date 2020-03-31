package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductController {

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

    @GetMapping("/categories")
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Category");
        categories.add("Category");
        categories.add("Category");
        return categories;
    }

    @GetMapping("/categories/{Id}")
    public String getCategoriesWithId(@PathVariable int id) {
        return String.format("Category: %s", id);
    }
}
