package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.CarroDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import com.entra21.Transportadora.view.service.CarroService;
import com.entra21.Transportadora.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroRestController {

    @Autowired
    private CarroService carroService;
    @Autowired
    CarroRepository carroRepository;

    @GetMapping
    public List<CarroDTO> getCarro(){
        return carroService.getAllCarros();
    }

    @PostMapping
    public void addCarro(@RequestBody CarroDTO Newcarro){
        carroService.saveCarros( Newcarro);
    }



    @DeleteMapping("/{id}")
    public void deleteCarros(@PathVariable(name = "id") Long idEmpresa) {
        carroService.deleteCarros(idEmpresa);
    }


    @PutMapping("/{id}")
    public CarroDTO updateCarro(@PathVariable(name = "id") Long idcarronv,
                                @RequestBody CarroDTO carroDTO) {
        return carroService.updateCarro(idcarronv,carroDTO);
    }

}



