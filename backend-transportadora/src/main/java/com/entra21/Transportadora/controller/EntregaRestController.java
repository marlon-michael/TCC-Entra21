package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Entrega.EntregaAddDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaUpDTO;
import com.entra21.Transportadora.model.entity.EntregaEntity;
import com.entra21.Transportadora.view.repository.EntregaRepository;
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

    @Autowired
    private EntregaRepository entregaRervice;


    @GetMapping
    public List<EntregaDTO> getEntrega(){
        return entregaService.getAllEntrega();
    }

    @GetMapping("/{id}")
    public Optional<EntregaEntity> getById(@PathVariable(name = "id") Long id){
        return entregaRervice.findById(id);
    }

    @GetMapping("/entregador/{cpf}")
    public List<EntregaDTO> getAllByEntregador(@PathVariable(name = "cpf") String cpf){
        return entregaService.getAllEntragaByEntregador(cpf);
    }

    //TODO: fazer retornar DTO
    @GetMapping("/empresa/{cnpj}")
    public List<EntregaEntity> getAllByEmpresa(@PathVariable(name = "cnpj") String cnpj){
        return entregaRervice.findAllByEntregador_Empresa_Cnpj(cnpj);
    }

    @PostMapping("/addEntrega")
    public String addEntrega(@RequestBody EntregaAddDTO NewEntrega){
        return entregaService.save(NewEntrega);
    }

    @PutMapping("/{id}")
    public EntregaUpDTO updateEntrega(@PathVariable(name = "id") Long idEntrega, @RequestBody EntregaUpDTO entrega){
        return entregaService.updateEntrega(idEntrega, entrega);
    }

    @DeleteMapping("/{id}")
    public void deleteEntrega(@PathVariable(name = "id") Long idEntrega) {
        entregaService.deleteEntrega(idEntrega);
    }

}
