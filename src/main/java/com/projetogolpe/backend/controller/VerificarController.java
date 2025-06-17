package com.projetogolpe.backend.controller;

import com.projetogolpe.backend.dto.BoletoRequest;
import com.projetogolpe.backend.service.VerificacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public ResponseEntity<String> verificarBoleto(@RequestBody BoletoRequest dto) {

        // logo no começo do método, antes do try {
        if (verificacaoService.isDestinatarioDenunciado(dto.getDestinatario())) {
            return ResponseEntity.ok(
                    "Inconsistência detectada. \n" + "Destinatário já denunciado por emissão de boletos falsos"
            );
        }

        try {
            // extrai apenas os 3 dígitos do código de banco (se vier no formato "Nome (123)")
            String raw = dto.getBanco().trim();
            Matcher m = Pattern.compile(".*\\((\\d{3})\\)$").matcher(raw);
            String codigoBanco = m.matches() ? m.group(1) : raw;

            // chama o serviço usando o getter correto:
                boolean valido = verificacaoService.verificarBanco(
                        codigoBanco,
                        dto.getCodigoDeBarras()
                );



            String cb = dto.getCodigoDeBarras().trim();
            String ultimos = cb.substring(cb.length() - 10);
            double valorDouble = Double.parseDouble(ultimos) / 100.0;
            String valorFormatado = String.format("%.2f", valorDouble);

            if (valido) {
                String msg =
                        "Boleto sem nenhuma inconsistência.\n"
                                + "Valor: R$ "    + valorFormatado + "\n"
                                + "Destinatário: " + dto.getDestinatario() + "\n";
                return ResponseEntity.ok(msg);
            } else {
                return ResponseEntity.ok("Boleto falso.\n" +
                        "Inconsistência detectada, código de barras \n " +
                        "não bate com o banco informado ");
            }



        } catch (IllegalStateException e) {
            // boleto já denunciado
            return ResponseEntity.ok(e.getMessage());

        } catch (IllegalArgumentException e) {
            // input inválido ou banco não cadastrado
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
