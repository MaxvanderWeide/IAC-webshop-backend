package com.persistence.storage;

import com.google.cloud.storage.*;
import com.model.category.Category;
import com.model.product.Product;
import com.service.ConfigSelector;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class StorageGCPFile implements StorageGCP {

    @Override
    public boolean uploadFile(MultipartFile file, int id) {

        Storage storage = StorageOptions.newBuilder().setProjectId(ConfigSelector.PROJECT_ID).build().getService();
        BlobId blobId = BlobId.of(ConfigSelector.BUCKET, String.valueOf(id));
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        try {
            storage.create(blobInfo, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println(
                "File " + id + " uploaded to bucket " + ConfigSelector.BUCKET); // TODO - Add logger
        return true;
    }

    @Override
    public Blob downloadFile(Product product) {
        Storage storage = StorageOptions.newBuilder().setProjectId(ConfigSelector.PROJECT_ID).build().getService();

        try {
            Blob blob = storage.get(BlobId.of(ConfigSelector.BUCKET, String.valueOf(product.getId())));

            System.out.println(
                    "Downloaded object "
                            + product.getId()
                            + " from bucket name "
                            + ConfigSelector.BUCKET); // TODO - Add logger
            return blob;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Blob downloadFile(Category category) {
        Storage storage = StorageOptions.newBuilder().setProjectId(ConfigSelector.PROJECT_ID).build().getService();

        try {
            Blob blob = storage.get(BlobId.of(ConfigSelector.BUCKET, String.valueOf(category.getId()) + "cat"));
            System.out.println(
                    "Downloaded object "
                            + category.getId() + "cat"
                            + " from bucket name "
                            + ConfigSelector.BUCKET); // TODO - Add logger
            return blob;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
