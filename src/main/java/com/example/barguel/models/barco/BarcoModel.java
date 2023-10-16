package com.example.barguel.models.barco;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "barco")
public class BarcoModel implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,unique = true,length = 50)
    private String nome;

    @Column(nullable = false)
    private TipoBarco tipoBarco;

    @Column(nullable = false)
    private Double tamanho;

    @Column(nullable = false)
    private Integer qtdPassageiros;

    @Column(nullable = false)
    private Double valorDiaria;

    public Double calculateAluguel(int numberOfDays){
        return numberOfDays * valorDiaria;
    }

    public UUID getId() {
        return id;
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
