package com.trabalhoFinalPOO.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;

@Entity
@Table(name= "tb_animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double idade;
    private String tipoAnimal;
    private Boolean vacinado;
    private Boolean castrado;

    private Boolean isAdopted;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference
    private Pessoa pessoa;

    public Animal(){}

    public Animal(Long id, String nome, Double idade, String tipoAnimal, Boolean vacinado, Boolean castrado,Boolean isAdopted) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.tipoAnimal = tipoAnimal;
        this.vacinado = vacinado;
        this.castrado = castrado;
        this.isAdopted = isAdopted;
    }
    public Animal(String nome, Double idade, String tipoAnimal, Boolean vacinado, Boolean castrado) {
        this.nome = nome;
        this.idade = idade;
        this.tipoAnimal = tipoAnimal;
        this.vacinado = vacinado;
        this.castrado = castrado;

    }

    public Animal(Animal animal) {
        this.nome = animal.getNome();
        this.idade= animal.getIdade();
        this.tipoAnimal = animal.getTipoAnimal();
        this.vacinado = animal.getVacinado();
        this.castrado = animal.getCastrado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
