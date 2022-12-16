package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.CarroDTO;
import com.entra21.Transportadora.view.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroRestController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<CarroDTO> getCarro(){
        return carroService.getAllCarros();
    }

    @GetMapping("/{placa}")
    public CarroDTO getCarroByPlaca(@PathVariable(name = "placa")String placa){
        return carroService.getCarroByPlaca(placa);
    }

    @GetMapping("/empresa/{cnpj_empresa}")
    public List<CarroDTO> getCarroByEmpresa(@PathVariable(name = "cnpj_empresa") String empresa_CNPJ){
        return carroService.getCarroByEmpresa_Cnpj(empresa_CNPJ);
    }

    @PostMapping
    public void addCarro(@RequestBody CarroDTO Newcarro){
        carroService.saveCarros(Newcarro);
    }

    @DeleteMapping("/{placa}")
    public void deleteCarros(@PathVariable(name = "placa") String placa) {
        carroService.deleteCarros(placa);
    }

    @PutMapping("/{placa}")
    public void updateCarro(@PathVariable(name = "placa") String placa, @RequestBody CarroDTO carroUpDTO) {
        carroService.updateCarro(placa,carroUpDTO);
    }

}



