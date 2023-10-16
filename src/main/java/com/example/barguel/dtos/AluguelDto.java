package com.example.barguel.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public class AluguelDto {
    @NotNull
    private UUID idCliente;
    @NotNull
    private UUID idBarco;
    @NotNull
    private LocalDate dataInicio;
    @NotNull
    private LocalDate dataFim;
    @NotNull
    private int qtdPassageiros;

    public AluguelDto(UUID idCliente, UUID idBarco, LocalDate dataInicio, LocalDate dataFim, int qtdPassageiros) {
        this.idCliente = idCliente;
        this.idBarco = idBarco;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.qtdPassageiros = qtdPassageiros;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public UUID getIdBarco() {
        return idBarco;
    }

    public void setIdBarco(UUID idBarco) {
        this.idBarco = idBarco;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }
}
