package dev.cleantho.bootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cleantho.bootcamp.models.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
