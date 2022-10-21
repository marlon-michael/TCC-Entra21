package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Carro.CarroAddDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroUpDTO;
import com.entra21.Transportadora.view.service.CarroService;
import com.entra21.Transportadora.view.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{placa}")
    public CarroDTO getCarroByPlaca(@PathVariable(name = "placa")String placa){
        return carroService.getCarroByPlaca(placa);
    }

<<<<<<< HEAD

    @GetMapping("/empresa/{cnpj_empresa}")
    public List<CarroDTO> getCarroByEmpresa(@PathVariable(name = "cnpj_empresa") String empresa_CNPJ){
        return carroService.getCarroByEmpresa_Cnpj(empresa_CNPJ);

=======
    @GetMapping("/empresa/{cnpj_empresa}")
    public List<CarroDTO> getCarroByEmpresa(@PathVariable(name = "cnpj_empresa") String empresa_CNPJ){
        return carroService.getCarroByEmpresa_Cnpj(empresa_CNPJ);
>>>>>>> 2c5ebfca49f4067c9735a65bb9213265c35ff608
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



