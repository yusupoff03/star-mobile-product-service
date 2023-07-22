package com.example.sofiyaproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SofiyaProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SofiyaProductServiceApplication.class, args);
    }

}
