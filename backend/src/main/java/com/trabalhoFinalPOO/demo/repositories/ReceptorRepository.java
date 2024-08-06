package com.trabalhoFinalPOO.demo.repositories;

import com.trabalhoFinalPOO.demo.entities.Pessoa;
import com.trabalhoFinalPOO.demo.entities.Receptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptorRepository extends JpaRepository<Receptor, Long> {
}
