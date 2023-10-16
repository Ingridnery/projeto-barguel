package com.example.barguel.models.cliente;

import com.example.barguel.models.aluguel.AluguelModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="cliente")
public class ClienteModel implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,unique = true,length = 40)
    private String nome;
    @Column(nullable = false,unique = true,length = 14)
    private String cpf;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String arraisAmador;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, orphanRemoval = false)
    private List<AluguelModel> alugueis = new ArrayList<>();

    public UUID getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArraisAmador() {
        return arraisAmador;
    }

    public void setArraisAmador(String arraisAmador) {
        this.arraisAmador = arraisAmador;
    }
}
