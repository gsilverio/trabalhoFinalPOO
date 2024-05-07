package com.trabalhoFinalPOO.demo.entities;

import java.util.Date;

public class Doador extends Pessoa{
    private Date date;

    public Doador(Date date) {
        this.date = date;
    }

    public Doador(Long id, String nome, String telefone, String email, Date date) {
        super(id, nome, telefone, email);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
