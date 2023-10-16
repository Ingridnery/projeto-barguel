package com.example.barguel.models.aluguel;

import com.example.barguel.models.barco.BarcoModel;
import com.example.barguel.models.cliente.ClienteModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="aluguel")
public class AluguelModel implements Serializable {
        private static final long serialVersionUID=1L;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "cliente.cpf")
        private ClienteModel cliente;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "barco_id", referencedColumnName = "id")
        private BarcoModel barco;

        @Column(nullable = false)
        private LocalDate dataInicio;

        @Column(nullable = false)
        private LocalDate dataFinal;

        @Column(nullable = false)
        private int qtdPassageiros;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public ClienteModel getCliente() {
            return cliente;
        }

        public void setCliente(ClienteModel cliente) {
            this.cliente = cliente;
        }

        public BarcoModel getBarco() {
            return barco;
        }

        public void setBarco(BarcoModel barco) {
            this.barco = barco;
        }

        public LocalDate getDataInicio() {
            return dataInicio;
        }

        public void setDataInicio(LocalDate dataInicio) {
            this.dataInicio = dataInicio;
        }

        public LocalDate getDataFinal() {
            return dataFinal;
        }

        public void setDataFinal(LocalDate dataFinal) {
            this.dataFinal = dataFinal;
        }

        public int getQtdPassageiros() {
            return qtdPassageiros;
        }

        public void setQtdPassageiros(int qtdPassageiros) {
            this.qtdPassageiros = qtdPassageiros;
        }
}
