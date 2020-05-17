package com.farmer.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MiaoshaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiaoshaServiceApplication.class, args);
    }

}
