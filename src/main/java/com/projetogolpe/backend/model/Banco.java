package com.projetogolpe.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "banco")
public class Banco {

    @Id
    @Column(name = "codigobanco", length = 3)
    private String codigoBanco;

    @Column (name = "banco", length = 50)
    private String nomeBanco;

    public Banco() {}

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }
}
