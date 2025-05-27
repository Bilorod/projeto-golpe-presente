package com.projetogolpe.backend.controller;

import com.projetogolpe.backend.dto.DenunciaDTO;      // renomeie BoletoRequest para DenunciaDTO, ou converta
import com.projetogolpe.backend.service.VerificacaoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VerificarController {

    private final VerificacaoService verificacaoService;

    public VerificarController(VerificacaoService verificacaoService) {
        this.verificacaoService = verificacaoService;
    }

    @PostMapping(
            value = "/verificar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> verificarBoleto(@RequestBody DenunciaDTO dto) {
        boolean valido = verificacaoService.processarDenuncia(dto);
        if (valido) {
            return ResponseEntity.ok("Boleto válido: " + dto.getCodigoBarras());
        } else {
            return ResponseEntity.ok("Boleto falso: inconsistência detectada");
        }
    }
}
