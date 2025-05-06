package com.projetogolpe.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificarController {

    @PostMapping ("/verificar")
    public String verificarBoleto(@RequestBody String codigoBarras ){
        return "Boleto verificado (mock): " + codigoBarras;
    }
}
