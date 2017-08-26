package com.foodworld.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.foodworld")
public class FoodApp {

    public static void main(String[] args) {
        SpringApplication.run(FoodApp.class, args);
    }

}
