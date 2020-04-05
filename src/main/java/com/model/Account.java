package com.model;

import java.util.Date;

public class Account {

    private int account;
    private Date createdOn;

    public Account(int id, Date createdOn) {
        this.account = id;
        this.createdOn = createdOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public int getAccount() {
        return account;
    }

}

