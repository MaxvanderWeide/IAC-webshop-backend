package com.domain;

import java.util.Date;

public class Account {

    private Date createdOn;

    public Account(Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isActive() {
        return false;
    }
}
