package com.model.product;

import com.google.cloud.storage.Blob;
import com.persistence.product.ProductDAO;
import com.persistence.product.ProductDAOImpl;
import com.persistence.storage.StorageGCP;
import com.persistence.storage.StorageGCPFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ProductServices implements ProductService {

    private ProductDAO productDAO;

    private ProductDAO getIacDao() {
        if (productDAO != null) {
            return productDAO;
        }
        return productDAO = new ProductDAOImpl();
    }

    @Override
    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            products.add(new Product(10, "naam", "beschrijving", 5.00, 1));
        }
        return products;
    }

    @Override
    public Product getProductWithId(int id) { return getIacDao().getProductWithId(id); }

    @Override
    public Product createProduct(Product product) {
        return getIacDao().saveProduct(product);
    }

    @Override
    public boolean uploadImage(Product product, MultipartFile file) {
        StorageGCP storageGCP = new StorageGCPFile();
        return storageGCP.uploadFile(file, product.getId());
    }

    @Override
    public Blob downloadImage(Product product) {
        StorageGCP storageGCP = new StorageGCPFile();
        return storageGCP.downloadFile(product.getId());
    }
}
