package com.model.cart;

public class CartItem {
    private int itemID;
    private int productID;
    private int amount;
    private int customerID;

    public CartItem(int itemID, int productID, int amount, int customerID) {
        this.itemID = itemID;
        this.productID = productID;
        this.amount = amount;
        this.customerID = customerID;
    }

    public int getProductID() {
        return productID;
    }

    public int getAmount() {
        return amount;
    }

    public int getCustomerID() {
        return customerID;
    }

    public CartItem setCustomerID(int customerID) {
        this.customerID = customerID;
        return this;
    }
}
