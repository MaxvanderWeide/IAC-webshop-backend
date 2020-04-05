package com.controller;

import com.google.api.client.util.Base64;
import com.google.cloud.storage.Blob;
import com.model.category.Category;
import com.model.category.CategoryService;
import com.model.category.CategoryServices;
import com.model.product.Product;
import com.model.product.ProductService;
import com.model.product.ProductServices;
import com.service.ConfigSelector;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private ProductService productService;

    private CategoryService getCategoryService() {
        if (categoryService != null) {
            return categoryService;
        }
        categoryService = new CategoryServices();
        return categoryService;
    }

    private ProductService getProductService() {
        if (productService != null) {
            return productService;
        }
        productService = new ProductServices();
        return productService;
    }


    @GetMapping("/{id}")
    public Map getCategoryWithId(@PathVariable int id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> products = new HashMap<>();
        Category category = getCategoryService().getCategoryWithId(id);
        for (Product p : getProductService().getProductsWithinCategory(id)) {
            products.put(p.getName(), String.format("%s/products/%s", ConfigSelector.APIURL, p.getId()));
        }
        Blob blob = getCategoryService().downloadImage(category);
        map.put("Producten", products);
        map.put("Image", blob != null ? Base64.encodeBase64String(blob.getContent()) : "None Found");
        map.put("Description", category.getDescription());
        map.put("Name", category.getName());
        return map;
    }
}
