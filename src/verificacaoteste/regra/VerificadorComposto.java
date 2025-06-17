package verificacaoteste.regra;

import verificacaoteste.model.Boleto;
import java.util.List;

public class VerificadorComposto implements Verificador {

    private final List<Verificador> verificadores;

    public VerificadorComposto(List<Verificador> verificadores) {
        this.verificadores = verificadores;
    }

    @Override
    public boolean verificar(Boleto boleto) {
        for (Verificador v : verificadores) {
            if (!v.verificar(boleto)) {
                return false;
            }
        }
        return true;
    }
}
