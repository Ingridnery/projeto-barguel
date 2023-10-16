package com.example.barguel.dtos;

import com.example.barguel.models.barco.TipoBarco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BarcoDto {
    @NotBlank
    private String nome;
    @NotNull
    private TipoBarco tipoBarco;
    @NotNull
    private Double tamanho;
    @NotNull
    private Integer qtdPassageiros;
    @NotNull
    private Double valorDiaria;

    public BarcoDto(String nome, TipoBarco tipoBarco, Double tamanho, Integer qtdPassageiros, Double valorDiaria) {
        this.nome = nome;
        this.tipoBarco = tipoBarco;
        this.tamanho = tamanho;
        this.qtdPassageiros = qtdPassageiros;
        this.valorDiaria = valorDiaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoBarco getTipoBarco() {
        return tipoBarco;
    }

    public void setTipoBarco(TipoBarco tipoBarco) {
        this.tipoBarco = tipoBarco;
    }

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    public Integer getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(Integer qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
