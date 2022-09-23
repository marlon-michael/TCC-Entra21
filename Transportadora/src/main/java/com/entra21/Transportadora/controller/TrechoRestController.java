package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.TrechoDTO;
import com.entra21.Transportadora.view.service.TrechoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TrechoRestController {

    @Autowired
    TrechoService trechoService;

    @GetMapping
    public List<TrechoDTO> getTrechos() {
        return trechoService.getAllTrecho();
    }
    @PostMapping
    public void addTrecho(@RequestBody TrechoDTO carroDTO){
        trechoService.saveTrecho(carroDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTrecho(@PathVariable(name = "id") Long id) {
        trechoService.deleteTrecho(id);
    }

    @PutMapping("/{id}")
    public TrechoDTO upTrecho(@PathVariable(name = "id")Long id,
                              @RequestBody TrechoDTO trechoDTO) {
        return trechoService.updateTrecho(id,trechoDTO);
    }
}
