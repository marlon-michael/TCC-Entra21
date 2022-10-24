package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.Item.ItemAddDTO;
import com.entra21.Transportadora.model.dto.Item.ItemDTO;
import com.entra21.Transportadora.model.dto.Item.ItemUpDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.entity.ItemEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.ItemRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<ItemDTO> getAllItem() {
        return itemRepository.findAll().stream().map(itemEntity -> {
            ItemDTO itemDTO = new ItemDTO();
            PessoaDTO pessoaDTO = null;

            if (itemEntity.getPessoa() != null){
                pessoaDTO = new PessoaDTO();
                pessoaDTO.setNome(itemEntity.getPessoa().getNome());
                pessoaDTO.setSobrenome(itemEntity.getPessoa().getSobrenome());
                pessoaDTO.setTelefone(itemEntity.getPessoa().getTelefone());
                pessoaDTO.setCpf(itemEntity.getPessoa().getCpf());
            }

            itemDTO.setPessoaItem(pessoaDTO);
            itemDTO.setLocalizador(itemEntity.getLocalizador());
            itemDTO.setLocalEntrega(itemEntity.getLocalEntrega());
            itemDTO.setNomeRecebedor(itemEntity.getNomeRecebedor());
            itemDTO.setStatus(itemEntity.getStatus());

            return itemDTO;
        }).collect(Collectors.toList());
    }

    public ItemDTO findByLocalizador(String localizador){
        ItemEntity itemEntity = itemRepository.findByLocalizador(localizador).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não foi encontrado!");});
        ItemDTO itemDTO = new ItemDTO();
        PessoaDTO pessoaDTO = null;

        if (itemEntity.getPessoa() != null){
            pessoaDTO = new PessoaDTO();
            pessoaDTO.setNome(itemEntity.getPessoa().getNome());
            pessoaDTO.setSobrenome(itemEntity.getPessoa().getSobrenome());
            pessoaDTO.setTelefone(itemEntity.getPessoa().getTelefone());
            pessoaDTO.setCpf(itemEntity.getPessoa().getCpf());
        }

        itemDTO.setPessoaItem(pessoaDTO);
        itemDTO.setLocalizador(itemEntity.getLocalizador());
        itemDTO.setLocalEntrega(itemEntity.getLocalEntrega());
        itemDTO.setNomeRecebedor(itemEntity.getNomeRecebedor());
        itemDTO.setStatus(itemEntity.getStatus());

        return itemDTO;
    }

//    public List<ItemDTO> findAllByPessoa_Cpf(String cpf){
//        return itemRepository.findAllByPessoa_Cpf(cpf).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa/Cpf não encontrado");}).stream().map(itemEntity -> {
//            ItemDTO itemDTO = new ItemDTO();
//            PessoaDTO pessoaDTO = new PessoaDTO();
//
//            if (itemEntity.getPessoa() != null){
//                pessoaDTO.setNome(itemEntity.getPessoa().getNome());
//                pessoaDTO.setSobrenome(itemEntity.getPessoa().getSobrenome());
//                pessoaDTO.setTelefone(itemEntity.getPessoa().getTelefone());
//                pessoaDTO.setCpf(itemEntity.getPessoa().getCpf());
//            }
//
//            itemDTO.setPessoaItem(pessoaDTO);
//            itemDTO.setLocalizador(itemEntity.getLocalizador());
//            itemDTO.setLocalEntrega(itemEntity.getLocalEntrega());
//            itemDTO.setNomeRecebedor(itemEntity.getNomeRecebedor());
//            itemDTO.setStatus(itemEntity.getStatus());
//
//            return itemDTO;
//        }).collect(Collectors.toList());
//    }
//toDO PARA VER
    public List<ItemDTO> findAllByPessoa_Cpf(Long IdUsuario){
        return itemRepository.findAllByPessoa_Cpf(IdUsuario).stream()
                .map(itemEntity -> {

                    ItemDTO itemDTO = new ItemDTO();
                    PessoaDTO pessoaDTO = new PessoaDTO();

                    pessoaDTO.setNome(itemEntity.getPessoa().getNome());
                    pessoaDTO.setSobrenome(itemEntity.getPessoa().getSobrenome());
                pessoaDTO.setTelefone(itemEntity.getPessoa().getTelefone());
                    pessoaDTO.setCpf(itemEntity.getPessoa().getCpf());


                    itemDTO.setLocalizador(itemEntity.getLocalizador());
                    itemDTO.setLocalEntrega(itemEntity.getLocalEntrega());
                    itemDTO.setNomeRecebedor(itemEntity.getNomeRecebedor());
                    itemDTO.setStatus(itemEntity.getStatus());

                    return itemDTO;
                }).collect(Collectors.toList());
    }


    public void saveItem(ItemAddDTO itemDTO) {
        PessoaEntity pessoaEntity = null;
        ItemEntity itemEntity = new ItemEntity();
        String UUId; //generating UUID
        do{
            UUId = UUID.randomUUID().toString();
        }while (itemRepository.existsByLocalizador(UUId));
        itemEntity.setLocalizador(UUId);
        itemEntity.setLocalEntrega(itemDTO.getLocalEntrega());
        itemEntity.setStatus("Preparando pedido");
        itemEntity.setNomeRecebedor(itemDTO.getNomeRecebedor());

        if (itemDTO.getPessoaItem() != null && itemDTO.getPessoaItem().getCpf() != null){
            pessoaEntity = pessoaRepository.findByCpf(itemDTO.getPessoaItem().getCpf()).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cpf/Pessoa não encontrada");});
        }
        itemEntity.setPessoa(pessoaEntity);

        itemRepository.save(itemEntity);
    }

    //TODO: UM ITEM DEVE DER DELETADO ???
    public void deleteItem(String localizador) {
        itemRepository.deleteById(
                itemRepository.findByLocalizador(localizador).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item/Localizador não encontrado")).getIdItem()
        );
    }

    public void itemUpDTO(String localizador, ItemUpDTO itemDTO) {
        ItemEntity itemEntity = itemRepository.findByLocalizador(localizador).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        if (itemDTO.getPessoaItem() != null){
            PessoaEntity pessoaDTO = pessoaRepository.findByCpf(itemDTO.getPessoaItem().getCpf()).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cpf/Entregador não encontrado");});
            itemEntity.setPessoa(pessoaDTO);
        }
        itemEntity.setStatus(itemDTO.getStatus());
        itemRepository.save(itemEntity);
    }

}
