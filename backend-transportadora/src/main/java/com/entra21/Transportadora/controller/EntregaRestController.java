package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.EntregaDTO;
import com.entra21.Transportadora.view.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entrega")
public class EntregaRestController {

    @Autowired
    private EntregaService entregaService;


    @GetMapping
    public List<EntregaDTO> getEntrega(){
        return entregaService.getAllEntrega();
    }

    @GetMapping("/{id}")
    public Optional<EntregaDTO> getById(@PathVariable(name = "id") Long id){
        return entregaService.findById(id);
    }

    @GetMapping("/entregador/{cpf}")
    public List<EntregaDTO> getAllByEntregador(@PathVariable(name = "cpf") String cpf){
        return entregaService.getAllEntragaByEntregador(cpf);
    }

    @GetMapping("/empresa/{cnpj}")
    public List<EntregaDTO> getAllByEmpresa(@PathVariable(name = "cnpj") String cnpj){
        return entregaService.findAllByEntregador_Empresa_Cnpj(cnpj);
    }

    @PostMapping
    public void addEntrega(@RequestBody EntregaDTO NewEntrega){
        entregaService.save(NewEntrega);
    }

    @PutMapping("/{id}")
    public EntregaDTO updateEntrega(@PathVariable(name = "id") Long idEntrega, @RequestBody EntregaDTO entrega){
        return entregaService.updateEntrega(idEntrega, entrega);
    }

    @DeleteMapping("/{id}")
    public void deleteEntrega(@PathVariable(name = "id") Long idEntrega) {
        entregaService.deleteEntrega(idEntrega);
    }

}
