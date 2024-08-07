package com.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

	@Value("${spring.application.name}")
	private String appName;

	@Value("${app.name}")
	private String profileAppName;

	@Value("${app.description}")
	private String appDescription;
	
	@Value("${spring.profiles.active}")
	private String activeProfile;

	@GetMapping("/profile")
	public Map<String, String> getActiveProfile() {
		Map<String, String> response = new HashMap<>();
		response.put("Application Name", appName);
		response.put("Profile App Name", activeProfile);
		response.put("Description", appDescription);
		return response;
	}
}
