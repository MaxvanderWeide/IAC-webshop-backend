package com.model.discount;

import com.model.product.Product;

import java.util.Date;
import java.util.List;

public class Discount {
    private int id;
    private Date from;
    private Date until;
    private Double price;
    private int product;
    private String description;
//    private List<Product> products;

//    public Discount(Date from, Date until, List<Product> products) {
//        this.from = from;
//        this.until = until;
//        this.products = products;
//    }

    public Discount(int id, Date from, Date until, Double price, int product, String descript){
        this.id = id;
        this.from = from;
        this.until = until;
        this.price = price;
        this.product = product;
        this.description = descript;
    }

    //Getters and Setters
    //From
    public Date getFrom() {return from;}
    public void setFrom(Date from) {this.from = from;}

    //Until
    public Date getUntil() {return until;}
    public void setUntil(Date until) {this.until = until;}

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
