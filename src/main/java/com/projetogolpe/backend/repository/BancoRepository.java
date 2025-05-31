package com.projetogolpe.backend.repository;

import com.projetogolpe.backend.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BancoRepository extends JpaRepository<Banco, String> {
    Optional<Banco> findByNomeBanco(String nomeBanco);
}
