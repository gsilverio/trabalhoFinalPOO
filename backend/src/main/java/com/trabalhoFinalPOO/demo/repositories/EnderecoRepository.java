package com.trabalhoFinalPOO.demo.repositories;

import com.trabalhoFinalPOO.demo.entities.Endereco;
import com.trabalhoFinalPOO.demo.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
