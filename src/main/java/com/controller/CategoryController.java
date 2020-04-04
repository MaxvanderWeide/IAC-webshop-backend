package com.controller;

import com.model.category.CategoryService;
import com.model.category.CategoryServices;
import com.model.product.Product;
import com.service.ConfigSelector;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    private CategoryService getCategoryService() {
        if (categoryService != null) {
            return categoryService;
        }
        return categoryService = new CategoryServices();
    }

    @GetMapping()
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Category");
        categories.add("Category");
        categories.add("Category");
        return categories;
    }

    @GetMapping("/{id}")
    public Map getCategoriesWithId(@PathVariable int id) {
        Map<String, Object> map = new HashMap<>();
        for (Product p : getCategoryService().getProductsWithinCategory(id)) {
            map.put(p.getName(), String.format("%s/products/%s", ConfigSelector.APIURL, p.getId()));
        }
        return map;
    }
}
