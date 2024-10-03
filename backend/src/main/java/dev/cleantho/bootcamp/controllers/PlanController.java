package dev.cleantho.bootcamp.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.cleantho.bootcamp.models.Plan;
import dev.cleantho.bootcamp.models.dto.PlanDTO;
import dev.cleantho.bootcamp.services.PlanService;

@RestController
@RequestMapping(value = "/plan")
public class PlanController {
	@Autowired
	private PlanService service;

	@PostMapping
	public ResponseEntity<Plan> insert(@RequestBody PlanDTO body) {
		Plan plan = service.insert(body);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(plan.getId()).toUri();
		return ResponseEntity.created(uri).body(plan);
	}

	@GetMapping
	public ResponseEntity<List<PlanDTO>> findAll() {
		List<PlanDTO> obj = service.findAll();		
		return ResponseEntity.ok().body(obj);
	}

}
