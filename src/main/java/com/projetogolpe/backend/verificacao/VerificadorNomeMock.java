package com.projetogolpe.backend.verificacao;

public class VerificadorNomeMock  implements VerificadorDeBoleto {
    @Override
    public boolean verificar(String codigoDeBarras) {
        System.out.println("Verificando nome do destinatário...");
        // Simulando a verificação. Exemplo: verificar se o nome é "suspeito"

        return true; // Simulando que o nome é valido
    }

}