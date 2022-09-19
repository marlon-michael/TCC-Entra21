package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.view.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaRestController {
    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping
    public List<EmpresaEntity> getEmpresa(){
        return empresaRepository.findAll();
    }
}