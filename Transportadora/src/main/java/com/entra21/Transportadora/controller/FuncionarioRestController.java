package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;
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


<<<<<<< HEAD
   @GetMapping("/{id}")
   public List<FuncionarioDTO> getAllByEmpresa(@PathVariable(name = "id")Long id){
      return funcionarioService.getAllFuncionariByEmpresa();
=======
   //pega os funcionarios da empresa
   @GetMapping("/empresa/{cnpj}")
   public List<FuncionarioDTO> getAllByEmpresa(@PathVariable(name = "cnpj")String cnpj){
      return funcionarioService.getAllFuncionariByEmpresa(cnpj);
>>>>>>> 2c5ebfca49f4067c9735a65bb9213265c35ff608
   }
//pega a pessoa
   @GetMapping("/{cpf}")
   public FuncionarioDTO getByCPF(@PathVariable(name = "cpf")String cpf){
      return funcionarioService.findByCpf(cpf);
   }

//   @GetMapping("/{cpf}")
//   public FuncionarioDTO getByCPF(@PathVariable(name = "cpf")String cpf){
//      return funcionarioService.findByCpf(cpf);
//   }

   @PostMapping
   public void addFuncionario(@RequestBody FuncionarioAddDTO funcionarioPayLoadDTO){
      funcionarioService.saveFuncionario(funcionarioPayLoadDTO);
   }

<<<<<<< HEAD
=======
   @DeleteMapping("/{cpf}")
   public void deleteFuncionarioByCPF(@PathVariable(name = "cpf")String cpf){
      funcionarioService.deleteByFuncionario(cpf);
   }

>>>>>>> 2c5ebfca49f4067c9735a65bb9213265c35ff608
}
