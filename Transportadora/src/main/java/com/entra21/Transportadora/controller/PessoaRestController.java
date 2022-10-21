package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Pessoa.LoginDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaLoad;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaUpDTO;
import com.entra21.Transportadora.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaRestController {

    @Autowired
    private PessoaService pessoaService;


    @GetMapping
    public List<PessoaDTO> getPessoas() {
        return pessoaService.getAll();
    }

<<<<<<< HEAD
    //TODO: FAZER RETORNAR BOOLEAN
    @GetMapping("/login")
    public PessoaLoad getLogin() {
        return pessoaService.buscarUsuarioLogado();
=======
    @PostMapping("/login")
    public PessoaDTO getLogin(@RequestBody LoginDTO login) {
        return new PessoaDTO(pessoaService.buscarLogin(login));
>>>>>>> 2c5ebfca49f4067c9735a65bb9213265c35ff608
    }

    @GetMapping("/{cpf}")
    public PessoaDTO getAllByCpf(@PathVariable(name = "cpf") String cpf) {
        return pessoaService.findByCpf(cpf);
    }

    @PostMapping("/cadastro")
    public void addPessoa(@RequestBody PessoaAddDTO newPessoa) {
        pessoaService.save(newPessoa);
    }

    @PutMapping("/{cpf}")
    public void updatePessoa(@PathVariable(name = "cpf") String cpf, @RequestBody PessoaUpDTO pessoaPayLoadDTO) {
        pessoaService.updatePessoa(cpf, pessoaPayLoadDTO);
    }

}



