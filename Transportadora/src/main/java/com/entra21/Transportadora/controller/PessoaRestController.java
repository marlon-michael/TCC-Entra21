package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import com.entra21.Transportadora.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaRestController {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<PessoaEntity> getPessoas(){
        return pessoaRepository.findAll();
    }

    @GetMapping("/user")
    public String getPessoa() {
        return pessoaService.buscarUsuarioLogado().getNome();
    }

    @PostMapping
    public void addPessoa(@RequestBody PessoaEntity pessoa) {
        pessoaRepository.save(pessoa);
    }

}