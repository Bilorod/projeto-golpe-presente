package com.projetogolpe.backend.dto;

public class DenunciaDTO {
    private String codigoBarras;
    private String banco;
    private String destinatario;
    private String nomeDenunciantes;
    private String descricao;
    private String CpfCnpj;
    private String valor;

    public DenunciaDTO() {

    }

    public DenunciaDTO(String codigoDeBarras, String nomeDenuncians, String descricao) {
        this.codigoBarras = codigoDeBarras;
        this.nomeDenunciantes = nomeDenunciantes;
        this.descricao = descricao;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNomeDenunciantes() {
        return nomeDenunciantes;
    }

    public void setNomeDenunciantes(String nomeDenunciantes) {
        this.nomeDenunciantes = nomeDenunciantes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getCpfCnpj() {
        return CpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        CpfCnpj = cpfCnpj;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
