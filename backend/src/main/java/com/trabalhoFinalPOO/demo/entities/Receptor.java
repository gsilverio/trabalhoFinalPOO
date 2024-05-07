package com.trabalhoFinalPOO.demo.entities;

import java.util.Date;

public class Receptor extends Pessoa{
    private Date dataAdocao;

    public Receptor(Date dataAdocao) {
        this.dataAdocao = dataAdocao;
    }

    public Receptor(Long id, String nome, String telefone, String email, Date dataAdocao) {
        super(id, nome, telefone, email);
        this.dataAdocao = dataAdocao;
    }

    public Date getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(Date dataAdocao) {
        this.dataAdocao = dataAdocao;
    }
}
