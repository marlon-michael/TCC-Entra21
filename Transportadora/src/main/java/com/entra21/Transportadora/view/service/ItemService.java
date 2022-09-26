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
<<<<<<< HEAD
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
=======

          PessoaDTO cr2 = new PessoaDTO();
            cr2.setNome(fr.getPessoa().getNome());
            cr2.setCpf(fr.getPessoa().getCpf());
            cr2.setTelefone(fr.getPessoa().getTelefone());
            cr2.setSobrenome(fr.getPessoa().getSobrenome());


//            if (fr.getPessoa() == null){
//                return dto;
//            }else{
//                dto.setPessoaItem(cr2);
//                return dto;
//            }
            return dto;
>>>>>>> 51e2cdcccc742e1f42841349a2957fefe74f01a0

        }).collect(Collectors.toList());
    }



    public void saveItem(ItemDTO input) {
        ItemEntity newEntity = new ItemEntity();
//        newEntity.setIdItem(input.getIdItem());
        newEntity.setLocalizador(input.getLocalizador());
        newEntity.setLocalEntrega(input.getLocalEntrega());
        newEntity.setNomeRecebedor(input.getNomeRecebedor());
        newEntity.setStatus(input.getStatus());
<<<<<<< HEAD

//        newEntity.setPessoa(pessoaRepository.findById(input.getPessoaItem().getIdPessoa()).get());

=======
//        newEntity.setPessoa(pessoaRepository.findById(input.getPessoaItem().getIdPessoa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
//        PessoaDTO cr2 = new PessoaDTO();
//        cr2.setNome(newEntity.getPessoa().getNome());
//        cr2.setCpf(newEntity.getPessoa().getCpf());
//        cr2.setTelefone(newEntity.getPessoa().getTelefone());
//        cr2.setSobrenome(newEntity.getPessoa().getSobrenome());
//
//        if (input.getPessoaItem() == null){
//            return ;
//        }else{
//            newEntity.setPessoa(cr2.);
//            return dto;
//        }
>>>>>>> 51e2cdcccc742e1f42841349a2957fefe74f01a0
        itemRepository.save(newEntity);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public ItemDTO updateStatusItem(Long id, String novoStatus) {
        ItemEntity e = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        e.setStatus(novoStatus);
        e = itemRepository.save(e);
        ItemDTO dto = new ItemDTO();
        dto.setStatus(e.getStatus());
//        dto.setIdItem(e.getIdItem());
        return dto;
    }

    public ItemDTO updateAllItem(Long id, ItemDTO itemDTO) {
        ItemEntity e = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        e.setStatus(itemDTO.getStatus());
        e.setNomeRecebedor(itemDTO.getNomeRecebedor());
        e.setLocalizador(itemDTO.getLocalizador());
        e.setLocalEntrega(itemDTO.getLocalEntrega());
<<<<<<< HEAD
        PessoaEntity pessoaDTO = new PessoaEntity();
        pessoaDTO.setIdPessoa(e.getPessoa().getIdPessoa());
        e.setPessoa(pessoaDTO);
        itemDTO.setIdItem(e.getIdItem());
=======
        e.setPessoa(pessoaRepository.findById(itemDTO.getPessoaItem().getIdPessoa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        e = itemRepository.save(e);
//        itemDTO.setIdItem(e.getIdItem());
>>>>>>> 51e2cdcccc742e1f42841349a2957fefe74f01a0
        return itemDTO;
    }




}
