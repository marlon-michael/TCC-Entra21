package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.FuncionarioDTO;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioRestController {

   @Autowired
   FuncionarioService funcionarioService;


   @GetMapping
   public List<FuncionarioDTO> getAllFuncionario() {
       return funcionarioService.getAllFuncionario();
   }
//
//   @PostMapping
//   public void addFuncionario(
//           @RequestBody FuncionarioDTO funcionarioDTO){
//      funcionarioService.saveFuncionario( funcionarioDTO);
//   }

//    @Autowired
//    private FuncionarioRepository funcionarioRepository;

//    @GetMapping
//    public List<FuncionarioEntity> getAllFuncionarios(){
//        return funcionarioRepository.findAll();
//    }
}
