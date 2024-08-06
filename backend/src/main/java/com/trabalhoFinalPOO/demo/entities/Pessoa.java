package com.trabalhoFinalPOO.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    private String senha;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa",orphanRemoval = true)
    @JsonManagedReference
    private Endereco end;

    @OneToMany(cascade =CascadeType.ALL ,mappedBy = "pessoa")
    @JsonManagedReference
    private Set<Animal> animais = new HashSet<>();


    public Pessoa(){

    }
    public Pessoa(Long id, String nome, String telefone, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public Pessoa(Long id, String nome, String telefone, String email, String senha, Endereco end, Set<Animal> animais) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.end = end;
        this.animais = animais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(Set<Animal> animais) {
        this.animais = animais;
    }

    public void addAnimal(Animal a){
        animais.add(a);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEnd() {
        return end;
    }

    public void setEnd(Endereco end) {
        this.end = end;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
