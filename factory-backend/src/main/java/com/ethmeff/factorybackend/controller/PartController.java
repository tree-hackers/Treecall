package com.ethmeff.factorybackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ethmeff.factorybackend.model.Part;
import com.ethmeff.factorybackend.service.PartService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class PartController {

	@Autowired
	private PartService service;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HttpStatus> addParts(@RequestBody List<Part> parts) throws Exception {
		service.addPart(parts);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
