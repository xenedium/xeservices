package com.abderraziq.inventoryservice;

import com.abderraziq.inventoryservice.entities.Product;
import com.abderraziq.inventoryservice.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository) {
		return args -> {
			for (int i = 0; i < 10; i++) {
				productRepository.save(
						Product.builder()
								.name("Computer " + i)
								.price(1200 + Math.random() * 10000)
								.quantity((int) (Math.random() * 100))
								.build());
			}
		};
	}
}
