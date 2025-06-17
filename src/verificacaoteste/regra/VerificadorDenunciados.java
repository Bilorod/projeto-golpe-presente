package verificacaoteste.regra;

import verificacaoteste.model.Boleto;
import java.util.Set;

public class VerificadorDenunciados implements Verificador {

    private final Set<String> cnpjsDenunciados;

    public VerificadorDenunciados(Set<String> cnpjsDenunciados) {
        this.cnpjsDenunciados = cnpjsDenunciados;
    }

    @Override
    public boolean verificar(Boleto boleto) {
        return !cnpjsDenunciados.contains(boleto.getCnpjDestinatario());
    }
}
