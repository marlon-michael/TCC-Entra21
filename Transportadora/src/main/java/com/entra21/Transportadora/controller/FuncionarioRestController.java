package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.FuncionarioPayLoadDTO;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioRestController {

   @Autowired
   FuncionarioService funcionarioService;

   @Autowired
   FuncionarioRepository funcionarioRepository;


   @GetMapping
   public List<FuncionarioDTO> getAllFuncionario() {
       return funcionarioService.getAllFuncionario();
   }

   @GetMapping("/{id}")
   public List<FuncionarioEntity> getAllByEmpresa(@PathVariable(name = "id")Long id){
      return funcionarioRepository.findAllByEmpresa_IdEmpresa(id).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não foi encontrada!");});
   }

   @PostMapping
   public void addFuncionario(
           @RequestBody FuncionarioPayLoadDTO funcionarioPayLoadDTO
   ){
      funcionarioService.saveFuncionario(funcionarioPayLoadDTO);
   }
//
////    @Autowired
////    private FuncionarioRepository funcionarioRepository;
//
////    @GetMapping
////    public List<FuncionarioEntity> getAllFuncionarios(){
////        return funcionarioRepository.findAll();
////    }
}
