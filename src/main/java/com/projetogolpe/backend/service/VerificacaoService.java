package com.projetogolpe.backend.service;

import com.projetogolpe.backend.dto.DenunciaDTO;
import com.projetogolpe.backend.model.Denunciado;
import com.projetogolpe.backend.repository.DenunciadoRepository;
import com.projetogolpe.backend.verificacao.VerificadorDeBoleto;
import com.projetogolpe.backend.verificacao.VerificadorBancoMock;
import com.projetogolpe.backend.verificacao.VerificadorDenunciadosMock;
import com.projetogolpe.backend.verificacao.VerificadorNomeMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificacaoService {

    @Autowired
    private DenunciadoRepository repo;

    // mocks de validação
    private VerificadorDeBoleto verificadorBanco      = new VerificadorBancoMock();
    private VerificadorDeBoleto verificadorNome       = new VerificadorNomeMock();
    private VerificadorDeBoleto verificadorDenunciados = new VerificadorDenunciadosMock();


    public boolean processarDenuncia(DenunciaDTO dto) {

        boolean valido = validar(dto);


        Denunciado entidade = new Denunciado();
        entidade.setCpfCnpj(dto.getCpfCnpj());
        entidade.setBanco(dto.getBanco());
        entidade.setCodigobarras(dto.getCodigoBarras());
        entidade.setValor(dto.getValor());
        repo.save(entidade);

        return valido;
    }


    private boolean validar(DenunciaDTO dto) {
        String codigo = dto.getCodigoBarras();
        boolean okBanco      = verificadorBanco.verificar(codigo);
        boolean okNome       = verificadorNome.verificar(codigo);
        boolean okDenunciados = verificadorDenunciados.verificar(codigo);
        return okBanco && okNome && okDenunciados;
    }
}
