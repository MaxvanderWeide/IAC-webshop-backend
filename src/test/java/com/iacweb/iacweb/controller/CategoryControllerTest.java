package com.iacweb.iacweb.controller;

import com.model.category.Category;
import com.persistence.category.CategoryDAO;
import com.persistence.category.CategoryDAOImpl;
import org.junit.Assert;
import org.junit.Test;

public class CategoryControllerTest {

    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Test
    public void testFindCategoryById()
    {
        Category category = categoryDAO.getCategoryById(6);

        Assert.assertEquals(6, category.getId());
        Assert.assertEquals("Nieuw", category.getName());
        Assert.assertEquals("Nieuw product.", category.getDescription());
    }
}
