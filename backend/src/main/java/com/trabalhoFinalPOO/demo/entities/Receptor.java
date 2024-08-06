package com.trabalhoFinalPOO.demo.entities;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="tb_receptor")
public class Receptor extends Pessoa{
    private String tipo;
    public Receptor() {

    }

    public Receptor(String tipo) {
        this.tipo = tipo;
    }

    public Receptor(Long id, String nome, String telefone, String email, String senha, String tipo) {
        super(id, nome, telefone, email, senha);
        this.tipo = tipo;
    }


    public Receptor(Pessoa entity, String tipo) {
        super(entity.getId(), entity.getNome(), entity.getSenha(), entity.getEmail(), entity.getTelefone(),entity.getEnd(),entity.getAnimais());
        this.tipo = tipo;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
