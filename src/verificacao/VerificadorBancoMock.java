package verificacao;


// Verifica se o banco emissor é confiável (simulação)
public class VerificadorBancoMock implements VerificadorDeBoleto {
    @Override
    public boolean verificar(String codigoDeBarras) {
        System.out.println("[Mock] Verificando banco emissor...");
        return true; // Simulando que o banco é confiável
    }

}
