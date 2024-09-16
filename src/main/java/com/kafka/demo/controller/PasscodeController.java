package com.kafka.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class PasscodeController {
	
	
	@PostMapping( 
	        path = "/getPasscode", consumes = "application/json", 
	        produces = "application/json")
	public void passcode(@RequestBody ObjectNode json)
	{
		RestTemplate restTemplate = new RestTemplate();
		String name=json.get("id").asText() ;

		String url = "http://localhost:8081/passcode/v1/otp";
		String requestJson = "{\"id\" : name}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		String answer = restTemplate.postForObject(url, entity, String.class);
		System.out.println(answer);
	}

}
