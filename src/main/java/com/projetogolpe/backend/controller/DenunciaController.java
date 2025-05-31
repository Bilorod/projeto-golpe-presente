// src/main/java/com/projetogolpe/backend/controller/DenunciaController.java
package com.projetogolpe.backend.controller;

import com.projetogolpe.backend.dto.DenunciaDTO;
import com.projetogolpe.backend.service.VerificacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class DenunciaController {

    private final VerificacaoService verificacaoService;

    public DenunciaController(VerificacaoService verificacaoService) {
        this.verificacaoService = verificacaoService;
    }

    @PostMapping("/denunciar")
    public ResponseEntity<String> denunciar(@RequestBody DenunciaDTO dto) {
        try {
            boolean valido = verificacaoService.processarDenuncia(dto);
            String msg = valido
                    ? "Denúncia registrada: boleto válido"
                    : "Denúncia registrada: boleto suspeito";
            return ResponseEntity.ok(msg);
        } catch (IllegalStateException e) {
            // duplicata ou destinatário já denunciado
            return ResponseEntity.ok(e.getMessage());

        } catch (IllegalArgumentException e) {
            // erro de validação
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
