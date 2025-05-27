package com.projetogolpe.backend.repository;

import com.projetogolpe.backend.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, String> {
}
