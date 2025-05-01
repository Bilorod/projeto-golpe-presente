package codigo;

import java.util.Date;

public class Boleto {
    private Long id;
    private String codigoBarras;
    private String bancoCodigo;
    private String bancoInformado;
    private Double valor;
    private Date vencimento;
    private String nomeBeneficiario;
    private String cnpjBeneficiario;
    private String resultadoVerificacao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getBancoCodigo() {
        return bancoCodigo;
    }

    public void setBancoCodigo(String bancoCodigo) {
        this.bancoCodigo = bancoCodigo;
    }

    public String getBancoInformado() {
        return bancoInformado;
    }

    public void setBancoInformado(String bancoInformado) {
        this.bancoInformado = bancoInformado;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    public String getCnpjBeneficiario() {
        return cnpjBeneficiario;
    }

    public void setCnpjBeneficiario(String cnpjBeneficiario) {
        this.cnpjBeneficiario = cnpjBeneficiario;
    }

    public String getResultadoVerificacao() {
        return resultadoVerificacao;
    }

    public void setResultadoVerificacao(String resultadoVerificacao) {
        this.resultadoVerificacao = resultadoVerificacao;
    }
}
