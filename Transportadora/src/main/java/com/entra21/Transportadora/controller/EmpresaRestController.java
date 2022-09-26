package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.EmpresaAddDTO;
import com.entra21.Transportadora.model.dto.EmpresaDTO;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import com.entra21.Transportadora.view.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {

    @Autowired
    EmpresaRepository empresaRepository;

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
    public EmpresaAddDTO updateEmpresa(@PathVariable(name = "id") Long idEmpresanv,
                                @RequestBody EmpresaAddDTO empresaDTO) {
        return empresaService.updateEmpresa(idEmpresanv, empresaDTO);
    }
}
