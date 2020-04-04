package com.persistence.storage;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.service.ConfigSelector;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

public class StorageGCPFile implements StorageGCP {

    @Override
    public String uploadFile(MultipartFile file) {
        String name = new Date().toString();
        Storage storage = StorageOptions.newBuilder().setProjectId(ConfigSelector.PROJECT_ID).build().getService();
        BlobId blobId = BlobId.of(ConfigSelector.BUCKET, name);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        try {
            storage.create(blobInfo, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println(
                "File " + name + " uploaded to bucket " + ConfigSelector.BUCKET);
        return "";
    }
}
