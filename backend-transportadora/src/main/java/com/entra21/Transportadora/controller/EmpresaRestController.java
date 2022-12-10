package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.EmpresaDTO;
import com.entra21.Transportadora.view.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {

    @Autowired
    private EmpresaService empresaService;


    @GetMapping
    public List<EmpresaDTO> getEmpresa(){
        return empresaService.getAllEmpresas();
    }

    @GetMapping("/{cnpj}")
    public EmpresaDTO getEmpresaByCNPJ(@PathVariable(name = "cnpj")String cnpj){
        return empresaService.findByCnpj(cnpj);
    }

    @GetMapping("/gerente/{cpf}")
    public EmpresaDTO findByGerente_cpf(@PathVariable(name = "cpf")String cpf){
        return empresaService.findByGerente_cpf(cpf);
    }

    //TODO: fazer retornar DTO
    @GetMapping("/funcionario/{cpf}")
    public EmpresaDTO findByFuncionario_cpf(@PathVariable(name = "cpf")String cpf){
        return empresaService.findByFuncionarios_Cpf(cpf);
    }

    @PostMapping
    public void addEmpresa(@RequestBody EmpresaDTO empresaDTO){
        empresaService.saveEmpresas(empresaDTO);
    }

    @DeleteMapping("/{cnpj}")
    public void deleteCarros(@PathVariable(name = "cnpj") String cnpj) {
        empresaService.deleteEmpresa(cnpj);
    }

    @PutMapping("/{cnpj}")
    public void updateEmpresa(@PathVariable(name = "cnpj") String cnpj, @RequestBody EmpresaDTO empresaDTO) {
        empresaService.updateEmpresa(cnpj, empresaDTO);
    }
}
