package dev.cleantho.bootcamp.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.cleantho.bootcamp.models.Client;
import dev.cleantho.bootcamp.models.dto.ClientDTO;
import dev.cleantho.bootcamp.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private ClientService service;

	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO body) {
		Client client = service.insert(body);		
		ClientDTO clientRes = ClientDTO.parser(client);			
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientRes.getId())
				.toUri();
		return ResponseEntity.created(uri).body(clientRes);
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> getAll() {
		List<Client> clients = service.findAll();
		List<ClientDTO> list = new ArrayList<>();
		for (Client client : clients) {
			list.add(ClientDTO.parser(client));
		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> find(@PathVariable Integer id) {
		Client obj = service.find(id);
		return ResponseEntity.ok().body(ClientDTO.parser(obj));
	}

}
