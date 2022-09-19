package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.entity.ItemEntity;
import com.entra21.Transportadora.view.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemRestController {
    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    public List<ItemEntity> getItens(){
        return itemRepository.findAll();
    }

    @GetMapping("/{localizador}")
    public List<ItemEntity> getItem(@PathVariable(name = "localizador") String localizador){
        return itemRepository.findByLocalizador(localizador);
    }

//    @PostMapping
//    public void addItem(@RequestBody ItemEntity item){
//        itemRepository.save(item);
//    }
}