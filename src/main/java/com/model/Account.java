package com.model;

import java.util.Date;

public class Account {
    private String account;
    private Date createdOn;

    public Account(String account, Date createdOn) {
        this.account = account;
        this.createdOn = createdOn;
    }

    public boolean isActive() {
        return false;
    }

    //Getters and Setters
    //Account
    public String getAccount() {return account;}
    public void setAccount(String account) {this.account = account; }

    //CreatedOn
    public Date getCreatedOn() {return createdOn;}
    public void setCreatedOn(Date createdOn) {this.createdOn = createdOn;}
}
