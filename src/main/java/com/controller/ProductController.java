package com.controller;

import com.model.product.Product;
import com.model.product.ProductService;
import com.model.product.ProductServices;
import io.jsonwebtoken.Claims;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Object, Object> getProducts(HttpServletRequest request) {
        Map<Object, Object> response = new HashMap<>();

        Claims claims = AuthController.decodeJWT(request.getHeader("authorization"));
        if (claims == null) {
            response.put(401, "Not authenticated");
            return response;
        }
        response.put("Product Name", "Product info?");
        return response;
    }

    @PostMapping()
    public HttpStatus createProduct(Authentication authentication, @RequestBody Product product) {
        System.out.println(authentication);
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
