package dev.cleantho.bootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.cleantho.bootcamp.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}