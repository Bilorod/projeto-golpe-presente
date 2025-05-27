package com.projetogolpe.backend.verificacao;

public enum Banco {
    ITAU ("Itaú", "341"),
    BRADESCO ("Bradesco","237"),
    NUBANK ("Nubank", "260");

    private final String nome;
    private final String codigo;

    Banco(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public static Banco fromNome(String nomeBanco) {
        for (Banco b :values()) {
            if(b.getNome().equalsIgnoreCase(nomeBanco.trim())) {
                return b;
            }
        }

        throw new IllegalArgumentException("Banco não cadastrado: \n" + nomeBanco);
    }
}
