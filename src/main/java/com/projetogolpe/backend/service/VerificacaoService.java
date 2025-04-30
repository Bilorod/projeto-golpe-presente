package com.projetogolpe.backend.service;

import org.springframework.stereotype.Service;

@Service
public class VerificacaoService {

    public String verificarBoleto(String codigoDeBarras){
        if (codigoDeBarras.startsWith("8")){
            return "Boleto suspeito! Pode ser um golpe";
        }else {
            return "Boleto aparentemente válido";
        }
    }

}
