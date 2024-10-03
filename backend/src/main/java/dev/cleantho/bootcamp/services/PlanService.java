package dev.cleantho.bootcamp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cleantho.bootcamp.models.Plan;
import dev.cleantho.bootcamp.models.PlanFactory;
import dev.cleantho.bootcamp.models.dto.PlanDTO;
import dev.cleantho.bootcamp.repositories.PlanRepository;


@Service
public class PlanService {
	@Autowired
	private PlanRepository repo;
	
	public Plan insert(PlanDTO obj) {		
		Plan plan = PlanFactory.createPlan(obj.getTipo());
		plan.setId(null);
		plan.setName(obj.getName());
		double price = 0.0;
		if (obj.getPrice().contains(",")) {
			price = Double.parseDouble(obj.getPrice().replace(',', '.'));
		} else {
			price = Double.parseDouble(obj.getPrice());
		}
		plan.setPrice(price);
		plan.setSize(obj.getSize());
		plan.setDescription(obj.getDescription());
		return repo.save(plan);
	}

	public List<PlanDTO> findAll() {
		List<Plan> plans = repo.findAll();
		if (plans == null) {
			throw new RuntimeException("Base de dados vazia");
		}
		List<PlanDTO> list = new ArrayList<>();
		for (Plan obj : plans) {
			list.add(PlanDTO.parser(obj));
		}
		return list;
	}
	
	public Plan findOne(Integer id) {
		return repo.getReferenceById(id);
	}
}
