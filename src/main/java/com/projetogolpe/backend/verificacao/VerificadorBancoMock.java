package com.projetogolpe.backend.verificacao;

// Verifica se o banco emissor é confiável (simulação)
public class VerificadorBancoMock implements VerificadorDeBoleto {
    @Override
    public boolean verificar(String codigoDeBarras) {
        System.out.println("Verificando banco emissor...");
        return true; // Simulando que o banco é confiável
    }

}