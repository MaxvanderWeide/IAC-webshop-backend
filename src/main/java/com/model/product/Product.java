package com.model.product;

import com.model.category.Category;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private List<Category> categories;

    public Product() {
    }

    public Product(int id, String name, String description, double price, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public List<Category> getCategories() {
        return categories;
    }

}
