package verificacaoteste.test;

import verificacaoteste.model.Boleto;
import verificacaoteste.service.VerificadorService;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class VerificadorTests {

    @Test
    public void testVerificacaoValida() {
        Set<String> denunciados = Set.of("11111111000199");
        String cnpjValido = "12345678000195";

        VerificadorService service = new VerificadorService(denunciados, cnpjValido);

        Boleto boletoValido = new Boleto("00112345678901234567890123456789012345678901", cnpjValido);
        assertTrue(service.verificarBoleto(boletoValido));
    }

    @Test
    public void testVerificacaoBancoInvalido() {
        Set<String> denunciados = Set.of();
        String cnpjValido = "12345678000195";

        VerificadorService service = new VerificadorService(denunciados, cnpjValido);

        Boleto boleto = new Boleto("99912345678901234567890123456789012345678901", cnpjValido);
        assertFalse(service.verificarBoleto(boleto));
    }

    @Test
    public void testVerificacaoCnpjInvalido() {
        Set<String> denunciados = Set.of();
        String cnpjValido = "12345678000195";

        VerificadorService service = new VerificadorService(denunciados, cnpjValido);

        Boleto boleto = new Boleto("00112345678901234567890123456789012345678901", "00000000000000");
        assertFalse(service.verificarBoleto(boleto));
    }

    @Test
    public void testVerificacaoDenunciado() {
        Set<String> denunciados = Set.of("12345678000195");
        String cnpjValido = "12345678000195";

        VerificadorService service = new VerificadorService(denunciados, cnpjValido);

        Boleto boleto = new Boleto("00112345678901234567890123456789012345678901", "12345678000195");
        assertFalse(service.verificarBoleto(boleto));
    }
}
