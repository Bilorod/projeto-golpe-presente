package verificacaoteste.model;

public class Boleto {
    private String codigoDeBarras;
    private String cnpjDestinatario;

    public Boleto(String codigoDeBarras, String cnpjDestinatario) {
        this.codigoDeBarras = codigoDeBarras;
        this.cnpjDestinatario = cnpjDestinatario;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public String getCnpjDestinatario() {
        return cnpjDestinatario;
    }
}
