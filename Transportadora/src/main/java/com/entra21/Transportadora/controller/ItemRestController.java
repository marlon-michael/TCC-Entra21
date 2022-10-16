//package com.entra21.Transportadora.controller;
//
//import com.entra21.Transportadora.model.entity.ItemEntity;
//import com.entra21.Transportadora.view.repository.ItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/item")
//public class ItemRestController {
//    @Autowired
//    ItemRepository itemRepository;
//
//    @GetMapping
//    public List<ItemEntity> getItens(){
//        return itemRepository.findAll();
//    }
//
//    @GetMapping("/{localizador}")
//    public List<ItemEntity> getItem(@PathVariable(name = "localizador") String localizador){
//        return itemRepository.findByLocalizador(localizador);
//    }
//
////    @PostMapping
////    public void addItem(@RequestBody ItemEntity item){
////        itemRepository.save(item);
////    }
//}
package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Item.ItemAddDTO;
import com.entra21.Transportadora.model.dto.Item.ItemDTO;
import com.entra21.Transportadora.model.dto.Item.ItemUpDTO;
import com.entra21.Transportadora.model.entity.ItemEntity;
import com.entra21.Transportadora.view.repository.ItemRepository;
import com.entra21.Transportadora.view.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemRestController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @GetMapping
    public List<ItemDTO> getItens() {
        return itemService.getAllItem();
    }

    @GetMapping("/{localizador}")
    public ItemDTO getItemByLocalizador(@PathVariable(name = "localizador") String localizador){
        return itemService.findByLocalizador(localizador);
    }

    @GetMapping("/pessoa/{cpf}")
    public List<ItemDTO> getItemByPessoa(@PathVariable(name = "cpf") String cpf){
        return itemService.findAllByPessoa_Cpf(cpf);
    }

    @PostMapping
    public void addItem(@RequestBody ItemAddDTO ItemDTO){
        itemService.saveItem(ItemDTO);
    }

    @DeleteMapping("/{localizador}")
    public void deleteItem(@PathVariable(name = "localizador") String localizador) {
        itemService.deleteItem(localizador);
    }

    @PutMapping("/{localizador}")
    public void updateItem(@PathVariable(name = "localizador") String localizador, @RequestBody ItemUpDTO itemDTO) {
        itemService.itemUpDTO(localizador, itemDTO);
    }
}

