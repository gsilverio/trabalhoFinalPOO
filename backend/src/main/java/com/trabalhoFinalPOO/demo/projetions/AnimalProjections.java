package com.trabalhoFinalPOO.demo.projetions;

import org.springframework.beans.factory.annotation.Value;

public interface AnimalProjections {

    Long getId();
    String getNome();
    Double getIdade();
    @Value("#{target.tipo_animal}")
    String getTipoAnimal();
    Boolean getVacinado();
    Boolean getCastrado();
    @Value("#{target.is_adopted}")
    Boolean getIsAdopted();
}
