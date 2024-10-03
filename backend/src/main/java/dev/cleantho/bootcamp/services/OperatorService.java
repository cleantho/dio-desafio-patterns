package dev.cleantho.bootcamp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cleantho.bootcamp.models.Client;
import dev.cleantho.bootcamp.models.Operator;
import dev.cleantho.bootcamp.repositories.ClientRepository;
import dev.cleantho.bootcamp.repositories.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository repo;
	
	@Autowired
	private ClientRepository repoClient;

	public Operator insert(Operator body) {
		Operator obj = Operator.getInstance();
		obj.setName(body.getName());
		repo.save(obj);
		return obj;
	}

	public Operator findOne() {
		Optional<Operator> obj = repo.findById(Operator.getInstance().getId());		
		return obj.orElseThrow(() -> new RuntimeException("Objeto não encontrado!"));	
	}
	
	protected void insertClient(Client obj) {
		Operator operator = Operator.getInstance();
		operator.addClient(obj);
		repo.save(operator);
	}

	public void notifyAll(String msn) {
		Operator operator = Operator.getInstance();
		operator.notifyClients(msn);
		repoClient.saveAllAndFlush(operator.getClients());		
	}
}
