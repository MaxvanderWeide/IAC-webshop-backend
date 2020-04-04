package com.model.product;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int categoryID;

    public Product() {
    }

    public Product(int id, String name, String description, double price, int categoryID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryID = categoryID;
    }

    //Getters and Setters
    //Id
    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    //Name
    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    //Price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //CategoryID
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String toString() {
        return String.format("id=%s, name=%s, description=%s, price=%s", id, name, description, price);
    }
}
