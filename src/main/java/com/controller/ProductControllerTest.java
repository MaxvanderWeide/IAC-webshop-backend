package com.controller;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class ProductControllerTest {

    @Test
    public void testgetProducts() throws Throwable{
        // Given
        ProductController test = new ProductController();

        // When
        List<String> result = test.getProducts();

        List<String> expected = Arrays.asList("Product", "Product", "Product");
        // THEN
        assertEquals(expected, result);
    }
}
