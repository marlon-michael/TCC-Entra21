package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Entrega.EntregaAddDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaUpDTO;
import com.entra21.Transportadora.view.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrega")
public class EntregaRestController {

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public List<EntregaDTO> getEntrega(){
        return entregaService.getAllEntrega();
    }

    @GetMapping("/entregador/{cpf}")
    public List<EntregaDTO> getAllByEntregador(@PathVariable(name = "cpf") String cpf){
        return entregaService.getAllEntragaByEntregador(cpf);
    }

    //TODO: TESTAR POST DE ENTREGA NOVAMENTE
    @PostMapping
    public void addEntrega(@RequestBody EntregaAddDTO NewEntrega){
        entregaService.save(NewEntrega);
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
