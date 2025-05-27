package com.projetogolpe.backend.model;

import jakarta.persistence.*;


@Entity
@Table(name = "denunciados")
public class Denunciado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column (name = "CPFCNPJ", length = 20)
    private String cpfCnpj;

    @Column(name = "banco", length = 50)
    private String banco;

    @Column(name = "codigobarras", length = 100, nullable = false, unique = true)
    private String codigobarras;

    @Column(name = "valor", length = 30)
    private String valor;

    public Denunciado() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCodigobarras() {
        return codigobarras;
    }

    public void setCodigobarras(String codigobarras) {
        this.codigobarras = codigobarras;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
