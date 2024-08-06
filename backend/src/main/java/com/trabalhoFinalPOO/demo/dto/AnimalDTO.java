package com.trabalhoFinalPOO.demo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.trabalhoFinalPOO.demo.entities.Pessoa;
import jakarta.persistence.*;


public class AnimalDTO {

    private Long id;
    private String nome;
    private Double idade;
    private String tipoAnimal;
    private Boolean vacinado;
    private Boolean castrado;

    private Boolean isAdopted;

    public AnimalDTO(){}

    public AnimalDTO(Long id, String nome, Double idade, String tipoAnimal, Boolean vacinado, Boolean castrado, Boolean isAdopted) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.tipoAnimal = tipoAnimal;
        this.vacinado = vacinado;
        this.castrado = castrado;
        this.isAdopted = isAdopted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getIdade() {
        return idade;
    }

    public void setIdade(Double idade) {
        this.idade = idade;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
    public Boolean getVacinado() {
        return vacinado;
    }

    public void setVacinado(Boolean vacinado) {
        this.vacinado = vacinado;
    }

    public Boolean getCastrado() {
        return castrado;
    }

    public void setCastrado(Boolean castrado) {
        this.castrado = castrado;
    }

    public Boolean getAdopted() {
        return isAdopted;
    }

    public void setAdopted(Boolean adopted) {
        isAdopted = adopted;
    }
}
