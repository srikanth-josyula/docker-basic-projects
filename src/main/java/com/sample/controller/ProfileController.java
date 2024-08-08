package com.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private static final Logger logger = LogManager.getLogger(ProfileController.class);

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.name}")
    private String profileAppName;

    @Value("${app.description}")
    private String appDescription;
 

    @GetMapping("/profile")
    public Map<String, String> getActiveProfile() {
        logger.info("Displaying Data from Properties file");
        Map<String, String> response = new HashMap<>();
        response.put("Application Name", appName);
        response.put("Description", appDescription);
        return response;
    }
}
