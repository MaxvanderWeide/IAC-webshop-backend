package com.model;

import com.model.product.Product;

import java.util.Date;
import java.util.List;

public class Discount {
    private Date from;
    private Date until;
    private List<Product> products;

    public Discount(Date from, Date until, List<Product> products) {
        this.from = from;
        this.until = until;
        this.products = products;
    }

    //Getters and Setters
    //From
    public Date getFrom() {return from;}
    public void setFrom(Date from) {this.from = from;}

    //Until
    public Date getUntil() {return until;}
    public void setUntil(Date until) {this.until = until;}
}
