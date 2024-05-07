package com.trabalhoFinalPOO.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name= "tb_animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double idade;
    private String animal;
    private Boolean vacinado;
    private Boolean castrado;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;



    public Animal(){}

    public Animal(Long id, String nome, Double idade, String animal, Boolean vacinado, Boolean castrado) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.animal = animal;
        this.vacinado = vacinado;
        this.castrado = castrado;
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

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
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
}
