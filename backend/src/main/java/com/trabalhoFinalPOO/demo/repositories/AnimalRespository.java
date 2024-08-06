package com.trabalhoFinalPOO.demo.repositories;

import com.trabalhoFinalPOO.demo.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRespository extends JpaRepository<Animal, Long> {
}
