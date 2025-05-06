package com.projetogolpe.backend.controller;

import com.projetogolpe.backend.dto.DenunciaDTO;
import com.projetogolpe.backend.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }


    @PostMapping("/denunciar")
    public String denunciarGolpe(@RequestBody DenunciaDTO dto){
        boolean valido = denunciaService.processarDenuncia(dto);
        if(valido){
            return "Denúncia registrada: boleto válido";
        } else{
            return "Denúncia registrada: boleto suspeito";
        }
    }


}
