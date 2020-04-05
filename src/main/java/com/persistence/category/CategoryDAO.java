package com.persistence.category;

import com.model.category.Category;

import java.util.List;

public interface CategoryDAO {


    Category getCategoryById(int id);

    List<Category> getCategoriesByProductId(int id);
}
