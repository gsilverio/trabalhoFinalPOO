package com.trabalhoFinalPOO.demo.repositories;

import com.trabalhoFinalPOO.demo.dto.AnimalDTO;
import com.trabalhoFinalPOO.demo.dto.PessoaDTO;
import com.trabalhoFinalPOO.demo.entities.Animal;
import com.trabalhoFinalPOO.demo.entities.Doador;
import com.trabalhoFinalPOO.demo.entities.Pessoa;
import com.trabalhoFinalPOO.demo.projetions.AnimalProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


    Pessoa searchByEmail(String email);

    @Query(nativeQuery = true, value = """
        SELECT TB_ANIMAL.NOME, TB_ANIMAL.CASTRADO, TB_ANIMAL.VACINADO, TB_ANIMAL.IDADE, TB_ANIMAL.ID, TB_ANIMAL.TIPO_ANIMAL, TB_ANIMAL.IS_ADOPTED FROM TB_ANIMAL
        INNER JOIN TB_PESSOA ON TB_PESSOA.ID = TB_ANIMAL.PESSOA_ID
        WHERE TB_PESSOA.ID = :id
    """)
    List<AnimalProjections> searchAnimalByProfile(Long id);
}
