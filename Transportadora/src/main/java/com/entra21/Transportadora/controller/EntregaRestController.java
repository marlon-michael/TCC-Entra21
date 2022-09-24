package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.EntregaDTO;
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


//    @GetMapping
//    public List<EntregaDTO> getEntrega(){
//        return entregaService.getAllEntrega();
//    }
    @GetMapping
    public List<EntregaEntity> getEntrega(){
        return entregaRepository.findAll();
    }

//    @PostMapping
//    public void addEntrega(@RequestBody EntregaDTO NewEntrega){
//        entregaService.saveEntrega(NewEntrega);
//    }
    @PostMapping
    public void addEntrega(@RequestBody EntregaEntity NewEntrega){
        entregaRepository.save(NewEntrega);
    }

    @DeleteMapping("/{id}")
    public void deleteEntrega(@PathVariable(name = "id") Long idEntrega) {
        entregaService.deleteEntrega(idEntrega);
    }

//    @PutMapping("/{id}")
//    public EntregaDTO updateEntrega(@PathVariable(name = "id") Long idEntrega,
//                                    @RequestBody EntregaDTO entregaDTO) {
//        return entregaService.updateEntrega(idEntrega, entregaDTO);
//    }
}
