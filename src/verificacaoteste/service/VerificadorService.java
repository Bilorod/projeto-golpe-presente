package verificacaoteste.service;

import verificacaoteste.model.Boleto;
import verificacaoteste.regra.*;

import java.util.List;
import java.util.Set;

public class VerificadorService {

    private final Verificador verificadorComposto;

    public VerificadorService(Set<String> cnpjsDenunciados, String cnpjDestinatarioValido) {
        VerificadorBanco verBanco = new VerificadorBanco();
        VerificadorCnpj verCnpj = new VerificadorCnpj(cnpjDestinatarioValido);
        VerificadorDenunciados verDenunciados = new VerificadorDenunciados(cnpjsDenunciados);

        this.verificadorComposto = new VerificadorComposto(List.of(verBanco, verCnpj, verDenunciados));
    }

    public boolean verificarBoleto(Boleto boleto) {
        return verificadorComposto.verificar(boleto);
    }
}
