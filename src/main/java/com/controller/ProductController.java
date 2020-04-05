package com.controller;

import com.google.api.client.util.Base64;
import com.google.cloud.storage.Blob;
import com.model.product.Product;
import com.model.product.ProductService;
import com.model.product.ProductServices;
import com.persistence.storage.StorageGCP;
import com.persistence.storage.StorageGCPFile;
import io.jsonwebtoken.Claims;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private StorageGCP storageGCP;

    private ProductService getProductService() {
        if (productService != null) {
            return productService;
        }
        return productService = new ProductServices();
    }

    private StorageGCP getStorageGCP() {
        if (storageGCP != null) {
            return storageGCP;
        }
        return storageGCP = new StorageGCPFile();
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
    public Map<Object, Object> createProduct(HttpServletRequest request, @RequestBody Product product) {
        Map<Object, Object> response = new HashMap<>();
        System.out.println(product);

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

    @GetMapping("/{id}/images")
    public String downloadImageUsingProduct(HttpServletRequest request,
                                          @PathVariable int id) {
        Product product = getProductService().getProductWithId(id);
        Blob blob = getProductService().downloadImage(product);
        return Base64.encodeBase64String(blob.getContent());
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
