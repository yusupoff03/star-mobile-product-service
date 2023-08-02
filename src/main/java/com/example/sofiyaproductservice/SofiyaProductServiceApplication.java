package com.example.sofiyaproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
//@EnableDiscoveryClient
@SpringBootApplication
//@ComponentScan(basePackages = "com.example")

public class SofiyaProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SofiyaProductServiceApplication.class, args);
    }

}
