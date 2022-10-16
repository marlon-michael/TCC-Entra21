package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Carro.CarroAddDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroUpDTO;
import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import com.entra21.Transportadora.view.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    public void addCarro(@RequestBody CarroAddDTO Newcarro){
        carroService.saveCarros(Newcarro);
    }

    @DeleteMapping("/{placa}")
    public void deleteCarros(@PathVariable(name = "placa") String placa) {
        carroService.deleteCarros(placa);
    }

    //TODO: FAZER RETORNAR VOID
    @PutMapping("/{placa}")
    public void updateCarro(@PathVariable(name = "placa") String placa, @RequestBody CarroUpDTO carroUpDTO) {
        carroService.updateCarro(placa,carroUpDTO);
    }

}



