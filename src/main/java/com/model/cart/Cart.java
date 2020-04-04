package com.model.cart;

public class Cart {
    private int itemID;
    private int productID;
    private int amount;
    private int customerID;

    public Cart(int itemID, int productID, int amount, int customerID) {
        this.itemID = itemID;
        this.productID = productID;
        this.amount = amount;
        this.customerID = customerID;
    }

    public int getItemID() { return itemID; }
    public void setItemID(int itemID) { this.itemID = itemID; }

    public int getProductID() { return productID; }
    public void setProductID(int productID) { this.productID = productID; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
}
