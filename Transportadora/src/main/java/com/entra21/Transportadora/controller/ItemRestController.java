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

import com.entra21.Transportadora.model.dto.ItemDTO;
import com.entra21.Transportadora.model.entity.ItemEntity;
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
    public ItemEntity getItem(@PathVariable(name = "localizador") String localizador){
        return itemRepository.findByLocalizador(localizador);
    }

    @PostMapping
    public void addItem(@RequestBody ItemDTO ItemDTO){
        itemService.saveItem(ItemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable(name = "id") Long id) {
        itemService.deleteItem(id);
    }

    @PutMapping("/status/{id}")
    public ItemDTO updateItem(@PathVariable(name = "id") Long id,

                              @RequestBody String novoStatus) {

        return itemService.updateStatusItem(id, novoStatus);
    }

    @PutMapping("/{id}")
    public ItemDTO updateItem(@PathVariable(name = "id") Long id,
                              @RequestBody ItemDTO itemDTO) {
        return itemService.updateAllItem(id, itemDTO);
    }
}

