package com.model;

import java.util.Date;

public class Account {

    private Date createdOn;

    public Account(Date createdOn) {
        this.createdOn = createdOn;
    }


    public boolean isActive() {
        return true;
    }

    //Getters and Setters

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}

