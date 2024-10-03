package dev.cleantho.bootcamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.cleantho.bootcamp.models.Operator;
import dev.cleantho.bootcamp.repositories.OperatorRepository;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private OperatorRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Operator op = Operator.getInstance();
		op.setName("Claro");
		repo.save(op);
	}

}
