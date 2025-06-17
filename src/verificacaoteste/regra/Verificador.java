package verificacao.regra;

import verificacao.model.Boleto;

public interface Verificador {
    boolean verificar(Boleto boleto);
}
