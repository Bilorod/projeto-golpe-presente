package com.projetogolpe.backend.dto;

public class DenunciaDTO {
    private String codigoBarras;
    private String nomeDenunciantes;
    private String descricao;

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
}
