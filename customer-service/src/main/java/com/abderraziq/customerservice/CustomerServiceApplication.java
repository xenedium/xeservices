package com.abderraziq.customerservice;

import com.abderraziq.customerservice.entities.Customer;
import com.abderraziq.customerservice.repo.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder().name("customer 1").email("c1@cs.com").build(),
					Customer.builder().name("customer 2").email("c2@cs.com").build(),
					Customer.builder().name("customer 3").email("c3@cs.com").build()
			));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
