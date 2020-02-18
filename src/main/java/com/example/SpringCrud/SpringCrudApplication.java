package com.example.SpringCrud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrudApplication {

    private static final Logger logger = LogManager.getLogger(SpringCrudApplication.class);
    public static void main(String[] args) {
        logger.info("Starting the Spring boot application");
        SpringApplication.run(SpringCrudApplication.class, args);
    }

}
