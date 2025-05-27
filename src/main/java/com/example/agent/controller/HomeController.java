package com.example.agent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    // This controller can be used to handle requests to the home page or root URL.
    // Currently, it does not contain any methods, but you can add methods to handle
    // specific requests as needed.

    // Example method to return a welcome message
     @GetMapping("/")
     public String home() {
         String buildVersion = System.getProperty("build.version", "unknown");
         String buildTimestamp = System.getProperty("build.timestamp", "unknown");
         System.out.println("Build Version: " + buildVersion);
         System.out.println("Build Timestamp: " + buildTimestamp);

         return "Welcome to the Job Agent Application!\n" +
                "Build Version: " + buildVersion + "\n" +
                "Build Timestamp: " + buildTimestamp;
     }
}
