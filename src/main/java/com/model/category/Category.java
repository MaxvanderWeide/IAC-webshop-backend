package com.model.category;

public class Category {

    private int id;
    private String name;
    private String description;
    private double price;
    private int imageId;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public Category setId(int id) {
        this.id = id;
        return this;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public Category setPrice(double price) {
        this.price = price;
        return this;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public Category setImageId(int imageId) {
        this.imageId = imageId;
        return this;
    }
}
