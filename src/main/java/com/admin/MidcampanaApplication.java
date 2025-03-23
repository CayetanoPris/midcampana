package com.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MidcampanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MidcampanaApplication.class, args);
	}

}
