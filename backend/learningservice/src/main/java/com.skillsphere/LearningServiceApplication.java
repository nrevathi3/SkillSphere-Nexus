package com.skillsphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class LearningServiceApplication {

    public static void main(String[] args) {

        SpringApplication app =
                new SpringApplication(LearningServiceApplication.class);

        Map<String, Object> properties = new HashMap<>();
        properties.put("server.port", "8082");

        app.setDefaultProperties(properties);
        app.run(args);
    }
}