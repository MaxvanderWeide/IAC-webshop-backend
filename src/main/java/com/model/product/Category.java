package com.model.product;

import java.util.List;

public class Category {

    private int id;
    private String name;
    private String description;
    private int imageId;

    public Category() {
    }

    public Category setId(int id) {
        this.id = id;
        return this;
    }

    public Category setName(String name) {
        this.name = name;
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

    public List<Product> getIncludedProducts() {
        ProductServices productServices = new ProductServices();
        return productServices.getProductsWithinCategory(this);
    }
}
