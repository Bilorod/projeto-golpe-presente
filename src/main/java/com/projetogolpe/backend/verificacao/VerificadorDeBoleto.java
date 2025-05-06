package com.projetogolpe.backend.verificacao;

public interface VerificadorDeBoleto {
    //Metodo que todos os verificadores devem implementar
    boolean verificar(String codigoDeBarras);
}