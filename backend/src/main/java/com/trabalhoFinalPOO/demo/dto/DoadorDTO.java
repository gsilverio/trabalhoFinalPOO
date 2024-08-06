package com.trabalhoFinalPOO.demo.dto;

public class DoadorDTO extends PessoaDTO{
    private String tipo;

    public DoadorDTO(String tipo) {
        this.tipo = tipo;
    }

    public DoadorDTO(Long id, String nome, String telefone, String email, String tipo) {
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
