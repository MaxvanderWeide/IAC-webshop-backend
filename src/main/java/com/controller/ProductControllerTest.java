package com.controller;

import static org.junit.Assert.assertEquals;

import com.model.product.Product;
import com.persistence.product.ProductDAO;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductControllerTest {

    @Autowired
    private ProductDAO productDao;

    @Test
    public void testFindById()
    {
        Product product = productDao.getProductWithId(5);

        Assert.assertEquals(5, product.getId());
        Assert.assertEquals("TestProduct", product.getName());
        Assert.assertEquals("Mooi test product", product.getDescription());
        Assert.assertEquals(30.00, product.getPrice());
    }

    @Test
    public void testSaveProduct()
    {
        Product product = new Product();
        product.setName("TestSaveProduct");
        product.setDescription("Dit is een Test");
        product.setPrice(10.00);

        productDao.saveProduct(product);
        int id = product.getId();

        Product newProduct = productDao.getProductWithId(id);

        Assert.assertEquals("TestSaveProduct", newProduct.getName());
        Assert.assertEquals("Dit is een Test", newProduct.getDescription());
        Assert.assertEquals(10.00, newProduct.getPrice());
    }
}
