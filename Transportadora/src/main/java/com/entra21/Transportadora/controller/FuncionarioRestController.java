package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.TrechoDTO;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.service.FuncionarioService;
import com.entra21.Transportadora.view.service.TrechoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioRestController {

   @Autowired
   FuncionarioService funcionarioService;

   @GetMapping
   public List<FuncionarioDTO> getAllFuncionario() {
//       return funcionarioService.;
      return null;
   }

//    @Autowired
//    private FuncionarioRepository funcionarioRepository;

//    @GetMapping
//    public List<FuncionarioEntity> getAllFuncionarios(){
//        return funcionarioRepository.findAll();
//    }
}
