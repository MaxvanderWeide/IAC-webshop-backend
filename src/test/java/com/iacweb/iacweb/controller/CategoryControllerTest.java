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
        Category category = categoryDAO.getCategoryById(3);

        Assert.assertEquals(3, category.getId());
        Assert.assertEquals("nieuw", category.getName());
        Assert.assertEquals("Test", category.getDescription());
    }
}
