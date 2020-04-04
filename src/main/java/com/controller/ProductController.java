package com.controller;

import com.model.category.CategoryService;
import com.model.category.CategoryServices;
import com.model.product.Product;
import com.model.product.ProductService;
import com.model.product.ProductServices;
import com.service.ConfigSelector;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    private ProductService getProductService() {
        if (productService != null) {
            return productService;
        }
        return productService = new ProductServices();
    }

    @GetMapping()
    public List<String> getProducts() {
        List<String> products = new ArrayList<>();
        products.add("Product");
        products.add("Product");
        products.add("Product");
        return products;
    }

    @PostMapping()
    public HttpStatus createProduct(@RequestBody Product product) {
        return getProductService().createProduct(product) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProductWithId(@PathVariable int id) {
        Product product = getProductService().getProductWithId(id);
        HashMap<String, Object> map = new HashMap<>();

        if (product == null) {
            map.put("message", "werkt niet broer");
            return map;
        }

        map.put("id", product.getId());
        map.put("name", product.getName());
        map.put("description", product.getDescription());
        map.put("price", product.getPrice());
        map.put("categoriy", product.getCategoryID());

        return map;

    }

    @GetMapping("/{id}/categories")
    public String getCategoriesWithProductId(@PathVariable int id) {
        return String.format("Category: %s", id);
    }
}
