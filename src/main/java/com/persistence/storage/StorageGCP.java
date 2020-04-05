package com.persistence.storage;

import com.google.cloud.storage.Blob;
import com.model.category.Category;
import com.model.product.Product;
import org.springframework.web.multipart.MultipartFile;

public interface StorageGCP {

    boolean uploadFile(MultipartFile file, int id);

    Blob downloadFile(Product product);

    Blob downloadFile(Category category);
}
