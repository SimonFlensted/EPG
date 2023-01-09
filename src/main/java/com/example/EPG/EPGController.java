package com.example.EPG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class EPGController {

	private EPGService epgService;
	
	@Autowired
	public EPGController(EPGService epgService) {
		this.epgService = epgService;
	}

	@GetMapping("/epg")
	public ResponseEntity<String> getHumanReadableEPG(@RequestBody String json) {
		try {
			return ResponseEntity.ok(epgService.GetHumanReadableEPG(json).toString());
		} catch (JsonProcessingException e) {
			return ResponseEntity.badRequest().body("Please provide correctly formatted json input");
		}
	}
}
