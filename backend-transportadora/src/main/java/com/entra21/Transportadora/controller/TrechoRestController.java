package com.entra21.Transportadora.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entra21.Transportadora.model.dto.TrechoDTO;
import com.entra21.Transportadora.view.service.TrechoService;

@RestController
@RequestMapping("/trecho")
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
    public TrechoDTO upTrecho(@PathVariable(name = "id")Long id, @RequestBody TrechoDTO trechoDTO) {
        return trechoService.updateTrecho(id,trechoDTO);
    }

}

