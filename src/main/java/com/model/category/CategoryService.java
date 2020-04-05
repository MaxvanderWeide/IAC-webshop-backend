package com.model.category;

import com.google.cloud.storage.Blob;

public interface CategoryService {

    Category getCategoryWithId(int id);

    Blob downloadImage(Category category);
}
