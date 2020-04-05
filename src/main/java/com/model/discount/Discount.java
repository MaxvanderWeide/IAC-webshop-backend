package com.model.discount;

import java.util.Date;

public class Discount {
    private int id;
    private Date from;
    private Date until;
    private Double price;
    private int product;
    private String description;

    public Discount(int id, Date from, Date until, Double price, int product, String descript) {
        this.id = id;
        this.from = from;
        this.until = until;
        this.price = price;
        this.product = product;
        this.description = descript;
    }

    public Date getFrom() {
        return from;
    }

    public Date getUntil() {
        return until;
    }

    public Double getPrice() {
        return price;
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

}
