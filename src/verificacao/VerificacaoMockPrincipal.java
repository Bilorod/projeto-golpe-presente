package verificacao;

public class VerificacaoMockPrincipal {
    public static void main(String[] args) {
        //Simulando um codigo de barras de boleto
        String boleto = "23793381286000000012345670000000001234567890";

        //Criando os verificadores simulados
        VerificadorDeBoleto banco = new VerificadorBancoMock();
        VerificadorDeBoleto nome = new VerificadorNomeMock();
        VerificadorDeBoleto denuncias = new VerificadorDenunciadosMock();

        System.out.println("Iniciando verificação mockada do boleto...\n");

        // Executando as verificações simuladas
        boolean resultado = banco.verificar(boleto) &&
                nome.verificar(boleto) &&
                denuncias.verificar(boleto);

        // Resultado final da verificação
        System.out.println("\nResultado final:");
        if (resultado) {
            System.out.println("✅ Boleto aparentemente legítimo (mockado)");
        } else {
            System.out.println("❌ Boleto suspeito (mockado)");
        }

    }
}
