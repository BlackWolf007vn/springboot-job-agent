package com.example.agent;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class AgentApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("build-info.properties")) {
            if (input != null) {
                props.load(input);
                props.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));
            }
        }
    }
}
