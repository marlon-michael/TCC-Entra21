package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Trecho.TrechoAddDTO;
import com.entra21.Transportadora.model.dto.Trecho.TrechoDTO;
import com.entra21.Transportadora.model.dto.Trecho.TrechoUpDTO;
import com.entra21.Transportadora.view.service.TrechoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void addTrecho(@RequestBody TrechoAddDTO carroDTO){
        trechoService.saveTrecho(carroDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTrecho(@PathVariable(name = "id") Long id) {
        trechoService.deleteTrecho(id);
    }

    //TODO: ENCONTRAR UM JEITO DO USUARIO TRECHO A ENTREGA (LOCALIZADOR DE ENTREGA???)
    @PutMapping("/{id}")
    public TrechoUpDTO upTrecho(@PathVariable(name = "id")Long id,
                                @RequestBody TrechoUpDTO trechoDTO) {
        return trechoService.updateTrecho(id,trechoDTO);
    }

}

