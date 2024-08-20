package com.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.SwapiClient;

@RestController
@RequestMapping("/api/people")
public class SwapiController {

	private final SwapiClient swapiClient;

	public SwapiController(SwapiClient swapiClient) {
		this.swapiClient = swapiClient;
	}

	@GetMapping("/{id}")
	public String getPersonById(@PathVariable String id) {
		return swapiClient.getPersonById(id);
	}
}
