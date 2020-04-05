package com.persistence.storage;

import com.google.cloud.storage.Blob;
import org.springframework.web.multipart.MultipartFile;

public interface StorageGCP {

    boolean uploadFile(MultipartFile file, int id);

    Blob downloadFile(int id);
}
