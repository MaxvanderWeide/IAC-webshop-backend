package com.model.product;

import com.google.cloud.storage.Blob;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    Product getProductWithId(int id);
    Product createProduct(Product product);
    boolean uploadImage(Product product, MultipartFile file);
    Blob downloadImage(Product product);
}
