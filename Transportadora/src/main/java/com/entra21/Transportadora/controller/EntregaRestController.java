package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.EntregaDTO;
import com.entra21.Transportadora.model.dto.EntregaPayloadDTO;
import com.entra21.Transportadora.model.entity.EntregaEntity;
import com.entra21.Transportadora.view.repository.EntregaRepository;
import com.entra21.Transportadora.view.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrega")
public class EntregaRestController {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public List<EntregaDTO> getEntrega(){
        return entregaService.getAllEntrega();
    }

    @PostMapping
    public void addEntrega(@RequestBody EntregaPayloadDTO NewEntrega){
        entregaService.save(NewEntrega);
    }

    @PutMapping("/{id}")
    public EntregaPayloadDTO updateEntrega(@PathVariable(name = "id") Long idEntrega, @RequestBody EntregaPayloadDTO entrega){
        return entregaService.updateEntrega(idEntrega, entrega);
    }

    @DeleteMapping("/{id}")
    public void deleteEntrega(@PathVariable(name = "id") Long idEntrega) {
        entregaService.deleteEntrega(idEntrega);
    }

}
