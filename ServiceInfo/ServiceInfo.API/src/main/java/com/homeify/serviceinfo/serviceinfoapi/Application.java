package com.homeify.serviceinfo.serviceinfoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.homeify.serviceinfo"})
@EnableMongoRepositories(basePackages = "com.homeify.serviceinfo.Repository")
@EntityScan(basePackages = {"com.homeify.serviceinfo.Model"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
