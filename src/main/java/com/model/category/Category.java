package com.model.category;

public class Category {

    private int id;
    private String description;

    public Category() {
    }

    public Category(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Category setId(int id) {
        this.id = id;
        return this;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return this.description;
    }
}
