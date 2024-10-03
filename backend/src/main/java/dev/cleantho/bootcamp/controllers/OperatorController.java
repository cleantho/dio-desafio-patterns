package dev.cleantho.bootcamp.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.cleantho.bootcamp.models.Operator;
import dev.cleantho.bootcamp.services.OperatorService;

@RestController
@RequestMapping(value = "/operator")
public class OperatorController {
	@Autowired
	private OperatorService service;

	@PutMapping
	public ResponseEntity<Operator> put(@RequestBody Operator body) {		
		Operator operator = service.insert(body);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(operator.getId())
				.toUri();
		return ResponseEntity.created(uri).body(operator);
	}

	@GetMapping
	public ResponseEntity<Operator> findAll() {		
		Operator obj = service.findOne();		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> notifyClients(@RequestBody String body){		
		service.notifyAll(body.substring(0, body.length()-1));
		return ResponseEntity.noContent().build();
	}
}
