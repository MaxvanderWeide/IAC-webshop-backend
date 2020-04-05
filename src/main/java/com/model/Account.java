package com.model;

import java.util.Date;

public class Account {
    private String accountName;
    private Date createdOn;

    public Account(String accountName, Date createdOn) {
        this.accountName = accountName;
        this.createdOn = createdOn;
    }

    public boolean isActive() {
        return false;
    }

    //Getters and Setters
    //Account
    public String getAccount() {
        return accountName;
    }

    public void setAccount(String account) {
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
