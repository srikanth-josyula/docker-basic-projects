package com.sample.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SwapiClient {

	private final RestTemplate restTemplate;

	public SwapiClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getPersonById(String id) {
		String url = "https://swapi.dev/api/people/" + id;
		return restTemplate.getForObject(url, String.class);
	}
}
