package com.abderraziq.customerservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class CustomerConfigTestController {
    @Value("${customer.params.p1}")
    private String customerP1;

    @Value("${customer.params.p2}")
    private String customerP2;

    @Value("${global.params.p1}")
    private String globalP1;

    @Value("${global.params.p2}")
    private String globalP2;

    @GetMapping("/params")
    public Map<String, String> params(){
        return Map.of(
                "customerP1", customerP1,
                "customerP2", customerP2,
                "globalP1", globalP1,
                "globalP2", globalP2
        );
    }
}
