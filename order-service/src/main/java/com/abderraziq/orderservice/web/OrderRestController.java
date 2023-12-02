package com.abderraziq.orderservice.web;

import com.abderraziq.orderservice.entities.Order;
import com.abderraziq.orderservice.model.Customer;
import com.abderraziq.orderservice.model.Product;
import com.abderraziq.orderservice.repo.OrderRepository;
import com.abderraziq.orderservice.repo.ProductItemRepository;
import com.abderraziq.orderservice.services.CustomerRestClientService;
import com.abderraziq.orderservice.services.InventoryRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    private final OrderRepository orderRepository;
    private final CustomerRestClientService customerRestClientService;
    private final InventoryRestClientService inventoryRestClientService;

    public OrderRestController(
            OrderRepository orderRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClientService customerRestClientService,
            InventoryRestClientService inventoryRestClientService
    ) {
        this.orderRepository = orderRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping("/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order = orderRepository.findById(id).get();
        Customer customer = customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi -> {
            Product product = inventoryRestClientService.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return order;
    }
}
