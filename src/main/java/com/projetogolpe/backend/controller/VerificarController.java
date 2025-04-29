package com.projetogolpe.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificarController {

    @GetMapping("/verificar")
    public String verificarBoleto(){
        return "Endpoint /verificar funcionando corretamente!";
    }
}
