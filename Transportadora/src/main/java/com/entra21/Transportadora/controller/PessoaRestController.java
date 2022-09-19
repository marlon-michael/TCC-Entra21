package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.PessoaDTO;
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
    public List<PessoaDTO> getPessoas() {
        return pessoaService.getAll();
    }

    @PostMapping
    public void addPessoa(@RequestBody PessoaDTO newPessoa) {
        pessoaService.save(newPessoa);
    }

    @PutMapping("/{id}")
    public PessoaDTO updatePessoa(@PathVariable(name = "id") Long id,
                                      @RequestBody PessoaDTO pessoaDTO) {
        return pessoaService.updatePessoa(id, pessoaDTO);
    }

//    @GetMapping("/{cpf}")
//    public String getPessoacpf() {
//        return pessoaService.buscarUsuarioLogado().getNome();
//    }

    //    @DeleteMapping("/{id}")
//    public void deletePessoa(@PathVariable(name = "id") Long id) {
//        pessoaService.delete(id);
//    }


//    @GetMapping
//    public List<PessoaEntity> getPessoas(){
//        return pessoaRepository.findAll();
//    }
//



//
//        return pessoaService.buscarUsuarioLogado().getSobrenome();
//        return pessoaService.buscarUsuarioLogado().getCpf();
//        return pessoaService.buscarUsuarioLogado().getTelefone();
//        return pessoaService.buscarUsuarioLogado().getUsername();

    // }
//
//
//


//
//    @PostMapping
//    public void addPessoa(@RequestBody PessoaEntity pessoa) {
//        pessoaRepository.save(pessoa);
//    }


}