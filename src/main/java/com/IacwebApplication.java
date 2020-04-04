package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class IacwebApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(IacwebApplication.class, args);
	}

}
