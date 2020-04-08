package com.iacweb.iacweb.controller;

import static org.junit.Assert.assertEquals;

import com.model.product.Product;
import com.persistence.product.ProductDAO;
import com.persistence.product.ProductDAOImpl;
import org.junit.*;

public class ProductControllerTest {

    private ProductDAO productDao = new ProductDAOImpl();

    @Test
    public void testFindById()
    {
        Product product = productDao.getProductWithId(1);

        Assert.assertEquals(1, product.getId());
        Assert.assertEquals("Mackie Big Knob Studio+ monitor controller/interface", product.getName());
        Assert.assertEquals("De meest uitgebreide versie van de Big Knob monitor controllers is de Big Knob Studio+. Deze controller bezit maar liefst toegang tot 4 geluidsbronnen en 3 monitorsets. De inmiddels befaamde grote volumeknop is overduidelijk het handelsmerk van de Big Knob-serie. Ook is de controller een heuse USB audio interface met 2 Onyx preamps. Hiermee zijn opnamen mogelijk in een audioresolutie van 24-bit bij een bitrate van 192 kHz. Op de controller is een handige Talkback-functie te vinden alsmede toegang tot smartphone- en tablet-bronnen via een 3.5 mm jack-ingang op het voorpaneel. Aan de achterzijde kan een hoofdtelefoonsysteem worden aangesloten voor distributie. Ten slotte toont een 16-segmenten LED-ingangsmeter de inkomende opnameniveaus.", product.getDescription());
        Assert.assertEquals(378.00, product.getPrice(), 0.001);
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
        Assert.assertEquals(10.00, newProduct.getPrice(), 0.001);
    }
}
