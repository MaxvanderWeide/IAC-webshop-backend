package com.persistence.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageGCP {

    String uploadFile(MultipartFile file);
}
