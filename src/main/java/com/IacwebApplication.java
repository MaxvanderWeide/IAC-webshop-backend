package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@SpringBootApplication
public class IacwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(IacwebApplication.class, args);
		Storage storage = StorageOptions.getDefaultInstance().getService();
	}

}
