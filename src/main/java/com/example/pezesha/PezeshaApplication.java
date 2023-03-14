package com.example.pezesha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = { "com.example.pezesha.entity" })
public class PezeshaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PezeshaApplication.class, args);
	}

}
