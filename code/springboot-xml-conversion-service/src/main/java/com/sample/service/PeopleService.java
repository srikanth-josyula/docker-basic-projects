package com.sample.service;

import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.model.Person;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

@Service
public class PeopleService {

    private final RestTemplate restTemplate;
    private final String appAUrl;

    public PeopleService(RestTemplate restTemplate, @Value("${app.a.url}") String appAUrl) {
        this.restTemplate = restTemplate;
        this.appAUrl = appAUrl;
    }

    public String getPersonById(String id) {
        StringWriter sw = new StringWriter();

        try {
            String jsonResponse = restTemplate.getForObject(appAUrl + "/api/people/" + id, String.class);

            // Parse JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Map JSON fields to Person object
            Person person = new Person();
            person.setName(rootNode.get("name").asText());
            person.setHeight(rootNode.get("height").asText());
            person.setMass(rootNode.get("mass").asText());
            person.setHairColor(rootNode.get("hair_color").asText());
            person.setSkinColor(rootNode.get("skin_color").asText());

            // Convert Person object to XML
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(person, sw);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sw.toString(); // Return the XML string
    }
}
