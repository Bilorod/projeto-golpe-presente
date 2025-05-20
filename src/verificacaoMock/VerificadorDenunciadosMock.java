package verificacaoMock;

public class VerificadorDenunciadosMock implements VerificadorDeBoleto {
    @Override
    public boolean verificar(String codigoDeBarras) {

            System.out.println("[Mock] Verificando lista de denunciados...");
            return false; // Simulando que foi encontrado na lista
        }
    }
