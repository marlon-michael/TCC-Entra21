package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.entity.EntregaEntity;
import com.entra21.Transportadora.view.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entrega")
public class EntregaRestController {

    @Autowired
    private EntregaRepository entregaRepository;

    @GetMapping
    public List<EntregaEntity> getAllEntrega() {
        return entregaRepository.findAll();
    }
}