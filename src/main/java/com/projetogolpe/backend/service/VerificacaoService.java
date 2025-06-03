package com.projetogolpe.backend.service;

import com.projetogolpe.backend.dto.DenunciaDTO;
import com.projetogolpe.backend.model.Denunciado;
import com.projetogolpe.backend.repository.BancoRepository;
import com.projetogolpe.backend.repository.DenunciadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VerificacaoService {

    @Autowired
    private BancoRepository bancoRepo;

    @Autowired
    private DenunciadoRepository denRepo;

    private static final Pattern PATRON_CODIGO = Pattern.compile(".*\\((\\d{3})\\)$");

    /**
     * Extrai apenas o código de 3 dígitos de uma string no formato "Nome (123)" ou retorna a própria string.
     */
    private String extrairCodigo(String raw) {
        raw = raw.trim();
        Matcher m = PATRON_CODIGO.matcher(raw);
        return m.matches() ? m.group(1) : raw;
    }

    /**
     * Extrai apenas o nome, removendo a parte " (123)" do fim da string.
     */
    private String extrairNome(String raw) {
        return raw.replaceAll("\\s*\\(\\d{3}\\)$", "").trim();
    }

    /**
     * Verifica se o prefixo do código de barras bate com o código de banco e se o banco existe.
     * @throws IllegalArgumentException em caso de código de barras inválido ou banco não cadastrado.
     */

    public boolean verificarBanco(String codigoBanco, String codigoBarras) {


        // 1) checa duplicata de código de barras
        if (denRepo.existsByCodigobarras(codigoBarras)) {
            throw new IllegalStateException("Este código de barras já foi denunciado anteriormente");
        }
        // 2) valida estrutura do código de barras
        if (codigoBarras == null || codigoBarras.length() < 3) {
            throw new IllegalArgumentException("Código de barras inválido");
        }
        String prefixo = codigoBarras.substring(0, 3);
        // 3) checa existência do banco
        if (!bancoRepo.existsById(codigoBanco)) {
            throw new IllegalArgumentException("Banco não cadastrado: " + codigoBanco);
        }
        return prefixo.equals(codigoBanco);
    }

    public boolean isDestinatarioDenunciado(String cpfCnpj) {
        return denRepo.existsByCpfCnpj(cpfCnpj);
    }


    /**
     * Processa a denúncia completa: valida e persiste os dados em 'denunciados'.
     * O campo 'banco' será gravado como o nome (sem o "(123)").
     * @return true se o boleto for válido (prefixo X código), false caso contrário.
     * @throws IllegalArgumentException para entradas inválidas.
     * @throws IllegalStateException se já houve denúncia para este código de barras.
     */
    public boolean processarDenuncia(DenunciaDTO dto) {
        String rawBanco    = dto.getBanco();
        String codigoBanco = extrairCodigo(rawBanco);
        String nomeBanco   = extrairNome(rawBanco);
        String codigoBarras = dto.getCodigoBarras().trim();

        // valida prefixo e existência do banco
        boolean valido = verificarBanco(codigoBanco, codigoBarras);


        // previne duplicatas
        if (denRepo.existsByCodigobarras(codigoBarras)) {
            throw new IllegalStateException("Boleto já denunciado anteriormente");
        }

        // persiste a denúncia
        Denunciado ent = new Denunciado();
        ent.setCpfCnpj(dto.getCpfCnpj());
        ent.setBanco(nomeBanco);
        ent.setCodigobarras(codigoBarras);
        ent.setValor(dto.getValor());

        try {
            denRepo.save(ent);
        } catch (DataIntegrityViolationException ex) {
            // garante não quebrar a aplicação se houver outra restrição
            throw new IllegalStateException("Erro ao salvar denúncia: " + ex.getMessage());
        }

        return valido;
    }
}
