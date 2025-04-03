package com.example.order_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = { "com.example.order_service", "com.example.hateoas" }) // Ensure correct package
public class OrderServiceApplication implements CommandLineRunner {

	@Value("${spring.datasource.url}")
	private String jdbcUrl;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("JDBC URL: " + jdbcUrl);
	}

}
