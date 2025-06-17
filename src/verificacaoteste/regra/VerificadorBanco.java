package verificacao.regra;

import verificacao.model.Boleto;
import java.util.Set;

public class VerificadorBanco implements Verificador {

    private static final Set<String> BANCOS_VALIDOS = Set.of("001", "033", "104", "237", "341", "399", "748");

    @Override
    public boolean verificar(Boleto boleto) {
        String codigo = boleto.getCodigoDeBarras();
        if (codigo == null || codigo.length() < 3) {
            return false;
        }
        String codigoBanco = codigo.substring(0, 3);
        return BANCOS_VALIDOS.contains(codigoBanco);
    }
}
