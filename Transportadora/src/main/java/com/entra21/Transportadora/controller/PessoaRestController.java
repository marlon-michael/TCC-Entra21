package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaUpDTO;
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
    public List<PessoaDTO> getPessoas() {
        return pessoaService.getAll();
    }

    @GetMapping("/{cpf}")
    public PessoaDTO getAllByCpf(@PathVariable(name = "cpf") String cpf) {
        return pessoaService.findByCpf(cpf);
    }

    @GetMapping("/funcionario/{byfuncionario}")
    public List<PessoaDTO> getAllByFuncionario() {
        return pessoaService.getAllByFuncionario();
    }

    @PostMapping
    public void addPessoa(@RequestBody PessoaAddDTO newPessoa) {
        pessoaService.save(newPessoa);
    }

    @PutMapping("/{id}")
    public PessoaUpDTO updatePessoa(@PathVariable(name = "id") Long id,
                                    @RequestBody PessoaUpDTO pessoaPayLoadDTO) {
        return pessoaService.updatePessoa(id, pessoaPayLoadDTO);
    }

}



