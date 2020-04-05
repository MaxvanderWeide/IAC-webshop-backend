package com.model.product;

import com.google.cloud.storage.Blob;
import com.persistence.product.ProductDAO;
import com.persistence.product.ProductDAOImpl;
import com.persistence.storage.StorageGCP;
import com.persistence.storage.StorageGCPFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductServices implements ProductService {

    private ProductDAO productDAO;

    private ProductDAO getIacDao() {
        if (productDAO != null) {
            return productDAO;
        }
        productDAO = new ProductDAOImpl();
        return productDAO;
    }

    @Override
    public List<Product> getProducts() {
        return getIacDao().getAllProducts();
    }

    @Override
    public Product getProductWithId(int id) {
        return getIacDao().getProductWithId(id);
    }

    @Override
    public Product createProduct(Product product) {
        return getIacDao().saveProduct(product);
    }

    @Override
    public boolean deleteProductById(int id) {
        return getIacDao().deleteProductById(id);
    }

    @Override
    public boolean uploadImage(Product product, MultipartFile file) {
        StorageGCP storageGCP = new StorageGCPFile();
        return storageGCP.uploadFile(file, product.getId());
    }

    @Override
    public Blob downloadImage(Product product) {
        StorageGCP storageGCP = new StorageGCPFile();
        return storageGCP.downloadFile(product);
    }

    @Override
    public List<Product> getProductsWithinCategory(int id) {
        return getIacDao().getProductsWithinCategory(id);
    }
}
