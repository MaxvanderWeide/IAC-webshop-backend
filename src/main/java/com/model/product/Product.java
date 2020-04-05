package com.model.product;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int categoryID;

    public Product() {
    }

    public Product(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(int id, String name, String description, double price, int categoryID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryID = categoryID;
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
    public int getCategoryID() {
        return categoryID;
    }

    public String toString() {
        return String.format("id=%s, name=%s, description=%s, price=%s, image=%s", id, name, description, price);
    }
}
