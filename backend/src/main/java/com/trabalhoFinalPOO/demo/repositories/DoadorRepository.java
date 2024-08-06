package com.trabalhoFinalPOO.demo.repositories;

import com.trabalhoFinalPOO.demo.entities.Doador;
import com.trabalhoFinalPOO.demo.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<Doador, Long> {
}
