package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaAddDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaUpDTO;
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

    @GetMapping("gerente/{cpf}")
    public EmpresaDTO findByGerente_cpf(@PathVariable(name = "cpf")String cpf){
        return empresaService.findByGerente_cpf(cpf);
    }

    @PostMapping
    public void addEmpresa(@RequestBody EmpresaAddDTO empresaDTO){
        empresaService.saveEmpresas(empresaDTO);
    }

    @DeleteMapping("/{cnpj}")
    public void deleteCarros(@PathVariable(name = "cnpj") String cnpj) {
        empresaService.deleteEmpresa(cnpj);
    }

    @PutMapping("/{cnpj}")
    public void updateEmpresa(@PathVariable(name = "cnpj") String cnpj, @RequestBody EmpresaUpDTO empresaDTO) {
        empresaService.updateEmpresa(cnpj, empresaDTO);
    }
}
