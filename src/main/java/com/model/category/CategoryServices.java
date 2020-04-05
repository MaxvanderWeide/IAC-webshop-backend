package com.model.category;

import com.google.cloud.storage.Blob;
import com.persistence.category.CategoryDAO;
import com.persistence.category.CategoryDAOImpl;
import com.persistence.storage.StorageGCP;
import com.persistence.storage.StorageGCPFile;


public class CategoryServices implements CategoryService {

    private CategoryDAO categoryDAO;

    private CategoryDAO getCategoryDAO() {
        if (categoryDAO != null) {
            return categoryDAO;
        }
        categoryDAO = new CategoryDAOImpl();
        return categoryDAO;
    }

    @Override
    public Category getCategoryWithId(int id) {
        return getCategoryDAO().getCategoryById(id);
    }

    @Override
    public Blob downloadImage(Category category) {
        StorageGCP storageGCP = new StorageGCPFile();
        return storageGCP.downloadFile(category);
    }
}
