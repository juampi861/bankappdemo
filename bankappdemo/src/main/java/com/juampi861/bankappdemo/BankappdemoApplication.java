package com.juampi861.bankappdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BankappdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankappdemoApplication.class, args);
    }

}
