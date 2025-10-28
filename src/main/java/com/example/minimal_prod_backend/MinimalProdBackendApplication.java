package com.example.minimal_prod_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MinimalProdBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinimalProdBackendApplication.class, args);
    }

}
