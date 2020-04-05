package com.controller;

import com.model.discount.Discount;
import com.persistence.discount.DiscountDAO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiscountControllerTest {

    @Autowired
    private DiscountDAO discountDAODao;

    @Test
    public void testFindById()
    {
        Discount discount = discountDAODao.getDiscountWithId(1);

        Date dateFrom = discount.getFrom();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDateFrom = dateFormat.format(dateFrom);

        Date dateUntil = discount.getUntil();
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd");
        String strDateUntil = dateFormat2.format(dateUntil);

        Assert.assertEquals("2020-04-01", strDateFrom);
        Assert.assertEquals("2020-04-08", strDateUntil);
        Assert.assertEquals(30.00, discount.getPrice(), 0.001);
        Assert.assertEquals(1, discount.getProduct());
        Assert.assertEquals("Reclametekst", discount.getDescription());
    }
}
