package com.model;

import java.util.Date;

public class Account {
    private int accountName;
    private Date createdOn;

    public Account(int accountName, Date createdOn) {
        this.accountName = accountName;
        this.createdOn = createdOn;
    }

    public boolean isActive() {
        return false;
    }

    //Getters and Setters
    //Account
    public int getAccount() {
        return accountName;
    }

    public void setAccount(int account) {
        this.accountName = account;
    }

    //CreatedOn
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
