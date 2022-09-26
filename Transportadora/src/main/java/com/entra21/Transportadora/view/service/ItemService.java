package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.ItemDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.entity.ItemEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.ItemRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<ItemDTO> getAllItem() {
        return itemRepository.findAll().stream().map(fr -> {
            ItemDTO dto = new ItemDTO();
            dto.setIdItem(fr.getIdItem());
            dto.setLocalizador(fr.getLocalizador());
            dto.setLocalEntrega(fr.getLocalEntrega());
            dto.setNomeRecebedor(fr.getNomeRecebedor());
            dto.setStatus(fr.getStatus());
            if (fr.getPessoa() == null){
                return dto;
            }else{
                PessoaDTO pessoaDTO = new PessoaDTO();
                pessoaDTO.setNome(dto.getPessoaItem().getNome());
                pessoaDTO.setSobrenome(dto.getPessoaItem().getSobrenome());
                pessoaDTO.setTelefone(dto.getPessoaItem().getTelefone());
                pessoaDTO.setCpf(dto.getPessoaItem().getCpf());
                dto.setPessoaItem(pessoaDTO);
                return dto;
            }

        }).collect(Collectors.toList());
    }

    public void saveItem(ItemDTO input) {
        ItemEntity newEntity = new ItemEntity();
        newEntity.setIdItem(input.getIdItem());
        newEntity.setLocalizador(input.getLocalizador());
        newEntity.setLocalEntrega(input.getLocalEntrega());
        newEntity.setNomeRecebedor(input.getNomeRecebedor());
        newEntity.setStatus(input.getStatus());

//        newEntity.setPessoa(pessoaRepository.findById(input.getPessoaItem().getIdPessoa()).get());

        itemRepository.save(newEntity);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public ItemDTO updateStatusItem(Long id, String novoStatus) {
        ItemEntity e = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        e.setStatus(novoStatus);
        e = itemRepository.save(e);
        ItemDTO dto = new ItemDTO();
        dto.setStatus(e.getStatus());
        dto.setIdItem(e.getIdItem());
        return dto;
    }

    public ItemDTO updateAllItem(Long id, ItemDTO itemDTO) {
        ItemEntity e = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        e.setStatus(itemDTO.getStatus());
        e.setNomeRecebedor(itemDTO.getNomeRecebedor());
        e.setLocalizador(itemDTO.getLocalizador());
        e.setLocalEntrega(itemDTO.getLocalEntrega());
        PessoaEntity pessoaDTO = new PessoaEntity();
        pessoaDTO.setIdPessoa(e.getPessoa().getIdPessoa());
        e.setPessoa(pessoaDTO);
        itemDTO.setIdItem(e.getIdItem());
        return itemDTO;
    }

}
