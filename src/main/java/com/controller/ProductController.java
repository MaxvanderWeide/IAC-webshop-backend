package com.controller;

import com.google.api.client.util.Base64;
import com.google.cloud.storage.Blob;
import com.model.product.Product;
import com.model.product.ProductService;
import com.model.product.ProductServices;
import io.jsonwebtoken.Claims;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
        productService = new ProductServices();
        return productService;
    }

    @GetMapping()
    public Map<Object, Object> getProducts(HttpServletRequest request) {
        Map<Object, Object> response = new HashMap<>();

//        Claims claims = AuthController.decodeJWT(request.getHeader("authorization"));
//        if (claims == null) {
//            response.put(401, "Not authenticated");
//            return response;
//        }
        for (Product product : getProductService().getProducts()) {
            response.put(product.getId(), product);
        }
        return response;
    }

    @PostMapping()
    public Map<Object, Object> createProduct(HttpServletRequest request, @RequestBody Product product) {
        Map<Object, Object> response = new HashMap<>();

        Claims claims = AuthController.decodeJWT(request.getHeader("authorization"));
        if (claims == null) {
            response.put(401, "Not authenticated");
            return response;
        }
        Product product1 = getProductService().createProduct(product);
        if (product1 == null) {
            response.put(400, "Product kon niet gemaakt worden");
            return response;
        }
        response.put("Product", product);
        return response;
    }

    @PostMapping("/{id}/images")
    public Map<Object, Object> createImageUsingProduct(HttpServletRequest request,
                                                       @RequestParam("file") MultipartFile file,
                                                       @PathVariable int id) {
        Map<Object, Object> response = new HashMap<>();
        Product product = getProductService().getProductWithId(id);

        if (getProductService().uploadImage(product, file)) {
            response.put("Image added", product);
            return response;
        }
        response.put(400, "Image couldn't be added");
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProductWithId(@PathVariable int id) {
        Product product = getProductService().getProductWithId(id);
        HashMap<String, Object> map = new HashMap<>();
        Blob blob = getProductService().downloadImage(product);

        if (product == null) {
            map.put("message", "Product couldn't be found");
            return map;
        }

        map.put("id", product.getId());
        map.put("name", product.getName());
        map.put("description", product.getDescription());
        map.put("price", product.getPrice());
        map.put("categories", product.getCategories());
        map.put("image", blob != null ? Base64.encodeBase64String(blob.getContent()) : "None found");

        return map;

    }

    @GetMapping("/{id}/categories")
    public String getCategoriesWithProductId(@PathVariable int id) {
        return String.format("Category: %s", id);
    }
}
