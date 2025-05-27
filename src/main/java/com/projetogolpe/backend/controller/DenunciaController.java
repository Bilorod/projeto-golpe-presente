package com.projetogolpe.backend.controller;

import com.projetogolpe.backend.dto.DenunciaDTO;
import com.projetogolpe.backend.service.VerificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class DenunciaController {

    @Autowired
    private VerificacaoService verificacaoService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }


    @PostMapping("/denunciar")
    public String denunciarGolpe(@RequestBody DenunciaDTO dto){
        boolean valido = verificacaoService.processarDenuncia(dto);
        if(valido){
            return "Denúncia registrada: boleto válido";
        } else{
            return "Denúncia registrada: boleto suspeito";
        }
    }


}
