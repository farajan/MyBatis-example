package com.example.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This application is a simple example of usage MyBatis.
 * There are two POJO: a user and a car. An application provides SELECT, INSERT, UPDATE, DELETE operations for both POJO.
 * Moreover users can own cars, it means they can buy it, sell it or transfer to another user.
 * Location of configuration for MyBatis: resources/mybatis-config.xml
 * Location of configuration for database: resources/application.properties
 * Application runs on port 8070.
 */
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
