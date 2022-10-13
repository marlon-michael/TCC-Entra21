package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
   public List<FuncionarioDTO> getAllByEmpresa(@PathVariable(name = "id")Long id){
      return funcionarioService.getAllFuncionarioById();
   }

   @PostMapping
   public void addFuncionario(
           @RequestBody FuncionarioAddDTO funcionarioPayLoadDTO
   ){
      funcionarioService.saveFuncionario(funcionarioPayLoadDTO);
   }

}
