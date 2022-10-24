package com.entra21.Transportadora.controller;

import com.entra21.Transportadora.model.dto.Item.ItemAddDTO;
import com.entra21.Transportadora.model.dto.Item.ItemDTO;
import com.entra21.Transportadora.model.dto.Item.ItemUpDTO;
import com.entra21.Transportadora.view.repository.ItemRepository;
import com.entra21.Transportadora.view.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/pessoa")
    public List<ItemDTO> getItemByPessoa(@RequestParam(name = "IdUsuario") Long IdUsuario){
        return itemService.findAllByPessoa_Cpf(IdUsuario);
    }

//    @GetMapping("/pessoa/{cpf}")
//    public List<ItemDTO> getItemByPessoa(@RequestParam(name = "IdItem") Long IdItem, @PathVariable(name = "cpf") String cpf){
//        return itemService.findAllByPessoa_Cpf(IdItem, cpf);
//    }
//TODO TESTE
//    @GetMapping("/ByPessoa")
//    public List<ItemDTO> getItemByPessoa(@RequestParam(name = "IdItem") Long IdItem){
//        return itemService.findAllByPessoa_Cpf(IdItem);
//    }

    @PostMapping("/additem")
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

