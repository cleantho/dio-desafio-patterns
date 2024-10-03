package dev.cleantho.bootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cleantho.bootcamp.models.Operator;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {

}
