package br.com.fiap.fintech.dto;

import java.time.LocalDate;

public class GastoRequestDTO {

    private String nome;
    private String descricao;
    private String tipo;
    private Double valor;
    private LocalDate dataGasto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo(){

        return this.tipo;
    }

    public void setTipo(String tipo){

        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataGasto() {
        return this.dataGasto;
    }

    public void setDataGasto(LocalDate dataGasto) {

        this.dataGasto = dataGasto;
    }
}
