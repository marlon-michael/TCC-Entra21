package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Carro.CarroAddDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroUpDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import com.entra21.Transportadora.view.service.CarroService;
import com.entra21.Transportadora.view.service.EmpresaService;
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

    @Autowired
    EmpresaService empresaService;

    @GetMapping
    public List<CarroDTO> getCarro(){
        return carroService.getAllCarros();
    }

    @GetMapping("/placa/{placa}")
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

    @DeleteMapping("/{id}")
    public void deleteCarros(@PathVariable(name = "id") Long idEmpresa) {
        carroService.deleteCarros(idEmpresa);
    }

    //TODO: FAZER RETORNAR VOID
    @PutMapping("/{id}")
    public CarroUpDTO updateCarro(@PathVariable(name = "id") Long idcarronv,
                                      @RequestBody CarroUpDTO carroUpDTO) {
        return carroService.updateCarro(idcarronv,carroUpDTO);
    }

}



