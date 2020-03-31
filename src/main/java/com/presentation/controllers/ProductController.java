package com.presentation.controllers;

import com.application.product.ProductService;
import com.application.product.ProductServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    private static ProductService productService;

    private static ProductService getProductService() {
        if (productService != null) {
            return productService;
        }
        productService = new ProductServices();
        return productService;
    }
    @GetMapping("/products")
    public List startDefault() {
        logger.trace("Call");
        return getProductService().getProducts();
    }
}
