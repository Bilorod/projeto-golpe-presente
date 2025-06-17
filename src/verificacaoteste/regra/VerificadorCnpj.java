package verificacaoteste.regra;

import verificacaoteste.model.Boleto;

public class VerificadorCnpj implements Verificador {

    private final String cnpjDestinatarioValido;

    public VerificadorCnpj(String cnpjDestinatarioValido) {
        this.cnpjDestinatarioValido = cnpjDestinatarioValido;
    }

    @Override
    public boolean verificar(Boleto boleto) {
        if (boleto.getCnpjDestinatario() == null) {
            return false;
        }
        return boleto.getCnpjDestinatario().equals(cnpjDestinatarioValido);
    }
}
