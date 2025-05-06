package com.projetogolpe.backend.service;

import com.projetogolpe.backend.dto.DenunciaDTO;
import com.projetogolpe.backend.verificacao.VerificadorBancoMock;
import com.projetogolpe.backend.verificacao.VerificadorDeBoleto;
import com.projetogolpe.backend.verificacao.VerificadorDenunciadosMock;
import com.projetogolpe.backend.verificacao.VerificadorNomeMock;
import org.springframework.stereotype.Service;

@Service
public class DenunciaService {

    private VerificadorDeBoleto verificadorBanco = new VerificadorBancoMock();
    private VerificadorDeBoleto verificadorNome = new VerificadorNomeMock();
    private VerificadorDeBoleto verificadorDenunciados = new VerificadorDenunciadosMock();

    public boolean processarDenuncia (DenunciaDTO dto){
        String codigo  = dto.getCodigoBarras();
        boolean okBanco = verificadorBanco.verificar(codigo);
        boolean okNome = verificadorNome.verificar(codigo);
        boolean okDenunciados = verificadorDenunciados.verificar(codigo);
        boolean resultado = okBanco && okNome && okDenunciados;
        return resultado;
    }
}

