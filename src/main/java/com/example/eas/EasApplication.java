package com.example.eas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.eas.*"})
public class EasApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasApplication.class, args);
    }

}
