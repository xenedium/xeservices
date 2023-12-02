package com.abderraziq.orderservice;

import com.abderraziq.orderservice.entities.Order;
import com.abderraziq.orderservice.entities.ProductItem;
import com.abderraziq.orderservice.enums.OrderStatus;
import com.abderraziq.orderservice.model.Customer;
import com.abderraziq.orderservice.model.Product;
import com.abderraziq.orderservice.repo.OrderRepository;
import com.abderraziq.orderservice.repo.ProductItemRepository;
import com.abderraziq.orderservice.services.CustomerRestClientService;
import com.abderraziq.orderservice.services.InventoryRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(
            OrderRepository orderRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClientService customerRestClientService,
            InventoryRestClientService inventoryRestClientService
    ) {
        return args -> {
            List<Customer> customers = customerRestClientService.allCustomers().getContent().stream().toList();
            List<Product> products = inventoryRestClientService.allProducts().getContent().stream().toList();
            Long customerId = 1L;
            Customer customer = customerRestClientService.customerById(customerId);
            for (int i = 0; i < 20; i++) {
                var savedOrder = orderRepository.save(Order.builder()
                        .customerId(customers.get((int)(Math.random() * 100) % customers.size()).getId())
                        .orderStatus(Math.random() > 0.5 ? OrderStatus.CREATED : OrderStatus.PENDING)
                        .createdAt(new Date())
                        .build());
                for (Product product : products) {
                    if (Math.random() > 0.7) {
                        productItemRepository.save(ProductItem.builder()
                                .order(savedOrder)
                                .productId(product.getId())
                                .price(product.getPrice())
                                .quantity((int) (Math.random() * 10))
                                .discount(Math.random())
                                .build());
                    }
                }
            }
        };
    }
}
