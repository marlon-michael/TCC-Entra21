package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.ItemDTO;
import com.entra21.Transportadora.model.entity.ItemEntity;
import com.entra21.Transportadora.view.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public void save(ItemDTO input) {
        ItemEntity newEntity = new ItemEntity();
        newEntity.setIdItem(input.getIdItem());
        newEntity.setLocalizador(input.getLocalizador());
        newEntity.setLocalEntrega(input.getLocalEntrega());
        newEntity.setNomeRecebedor(input.getNomeDestinatário());
        newEntity.setStatus(input.getStatus());
        itemRepository.save(newEntity);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
