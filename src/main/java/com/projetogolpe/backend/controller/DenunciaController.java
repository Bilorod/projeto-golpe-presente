package com.projetogolpe.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DenunciaController {
    @PostMapping("/denunciar")
    public String denunciarGolpe(@RequestBody String dadosGolpe){
        return "Golpe denunciado com sucesso!" + dadosGolpe;
    }


}
