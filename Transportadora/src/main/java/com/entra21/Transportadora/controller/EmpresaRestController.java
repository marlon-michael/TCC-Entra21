package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaAddDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaUpDTO;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
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

    @PostMapping
    public void addEmpresa(@RequestBody EmpresaAddDTO empresaDTO){
        empresaService.saveEmpresas(empresaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCarros(@PathVariable(name = "id") Long idEmpresa) {
        empresaService.deleteEmpresa(idEmpresa);
    }

    @PutMapping("/{id}")
    public EmpresaUpDTO updateEmpresa(@PathVariable(name = "id") Long idEmpresanv,
                                       @RequestBody EmpresaUpDTO empresaDTO) {
        return empresaService.updateEmpresa(idEmpresanv, empresaDTO);
    }
}
