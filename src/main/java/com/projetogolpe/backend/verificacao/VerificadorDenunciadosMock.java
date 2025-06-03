package com.projetogolpe.backend.verificacao;

public class VerificadorDenunciadosMock implements VerificadorDeBoleto {
    @Override
    public boolean verificar(String codigoDeBarras) {

        System.out.println("Verificando lista de denunciados...");
        return false; // Simulando que foi encontrado na lista
    }
}