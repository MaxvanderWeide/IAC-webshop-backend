package com.iacweb.iacweb.controller;

import com.model.discount.Discount;
import com.persistence.discount.DiscountDAO;
import com.persistence.discount.DiscountDAOImpl;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiscountControllerTest {

    private DiscountDAO discountDAO = new DiscountDAOImpl();

    @Test
    public void testFindById()
    {
        Discount discount = discountDAO.getDiscountWithId(3);

        Date dateFrom = discount.getFrom();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDateFrom = dateFormat.format(dateFrom);

        Date dateUntil = discount.getUntil();
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String strDateUntil = dateFormat2.format(dateUntil);

        Assert.assertEquals("2020-04-01", strDateFrom);
        Assert.assertEquals("2020-04-08", strDateUntil);
        Assert.assertEquals(81.50, discount.getPrice(), 0.001);
        Assert.assertEquals(1, discount.getProduct());
        Assert.assertEquals("Reclametekst", discount.getDescription());
    }
}
