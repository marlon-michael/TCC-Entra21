package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroRestController {
    @Autowired
    CarroRepository carroRepository;

    @GetMapping
    public List<CarroEntity> getCarro(){
        return carroRepository.findAll();
    }

    @PostMapping
    public void addCarro(@RequestBody CarroEntity carro){
        carroRepository.save(carro);
    }
}
