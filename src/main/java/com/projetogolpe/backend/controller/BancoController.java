package com.projetogolpe.backend.controller;

import com.projetogolpe.backend.model.Banco;
import com.projetogolpe.backend.repository.BancoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BancoController {


    private final BancoRepository bancoRepo;

    public BancoController(BancoRepository bancoRepo) {
        this.bancoRepo = bancoRepo;
    }

    @GetMapping("/bancos")
    public List<Banco> listaBancos() {
        return bancoRepo.findAll();
    }
}
