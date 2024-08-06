package com.trabalhoFinalPOO.demo.entities;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
@Entity
@Table(name = "tb_doador")
public class Doador extends Pessoa{
    private String tipo;
    public Doador() {

    }

    public Doador(Long id, String nome, String telefone, String email, String senha, String tipo) {
        super(id, nome, telefone, email, senha);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
