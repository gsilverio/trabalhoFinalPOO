package com.trabalhoFinalPOO.demo.dto;

public class ReceptorDTO extends PessoaDTO{
    private String tipo;

    public ReceptorDTO(String tipo) {
        this.tipo = tipo;
    }

    public ReceptorDTO(Long id, String nome, String telefone, String email, String tipo) {
        super(id, nome, telefone, email);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
