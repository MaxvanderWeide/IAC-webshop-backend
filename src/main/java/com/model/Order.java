package com.model;

import com.model.product.Product;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date date;
    private double totalPrice;
    private List<Product> products;

    public Order(int id, Date date, double totalPrice, List<Product> products) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public double getCurrentOrderValue() {return 0;}

    //Getters and Setters
    //Id
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    //Date
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    //Total Price
    public double getTotalPrice() {return totalPrice;}
    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}
}

