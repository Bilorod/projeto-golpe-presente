package com.projetogolpe.backend.repository;

import com.projetogolpe.backend.model.Denunciado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenunciadoRepository extends JpaRepository<Denunciado, Long> {
    boolean existsByCodigobarras(String codigobarras);
    boolean existsByCpfCnpj(String cpfCnpj);

}

