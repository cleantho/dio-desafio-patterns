package dev.cleantho.bootcamp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cleantho.bootcamp.models.Client;
import dev.cleantho.bootcamp.models.dto.ClientDTO;
import dev.cleantho.bootcamp.repositories.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private PlanService plServ;
	@Autowired
	private OperatorService opServ;
	@Autowired
	private ClientRepository repo;

	public Client insert(ClientDTO obj) {
		Client client = new Client(obj.getName());
		client.setId(null);
		client.setOperador(opServ.findOne());
		// Somente para isso foi criado o ClientDTO
		client.setPicked(plServ.findOne(obj.getPicked()));
		opServ.insertClient(client);
		return repo.save(client);
	}

	public List<Client> findAll() {
		List<Client> list = repo.findAll();
		if (list == null) {
			throw new RuntimeException("Base de dados vazia");
		}
		return list;
	}

	public Client find(Integer id) {	
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new RuntimeException(
				"Objeto não encontrado! Id: " + id));
	}

}
