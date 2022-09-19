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

//    @DeleteMapping("/{id}")
//    public void deletePessoa(@PathVariable(name = "id") Long id) {
//        pessoaService.delete(id);
//    }


    @PutMapping("/{id}")
    public PessoaDTO updatePessoa(@PathVariable(name = "id") Long id,
                                  @RequestBody String novoNome, String novoSobrenome, String novoTelefone, String novoCPF,  String novoLogin,  String novoSenha) {
        return pessoaService.updatePessoa(id, novoNome, novoSobrenome, novoTelefone, novoCPF, novoLogin, novoSenha);
    }


//    @GetMapping
//    public List<PessoaEntity> getPessoas(){
//        return pessoaRepository.findAll();
//    }
//



//    @GetMapping("/user")
//    public String getPessoa() {
//        return pessoaService.buscarUsuarioLogado().getNome() ;
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