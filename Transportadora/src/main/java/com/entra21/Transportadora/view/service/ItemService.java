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
            PessoaDTO pessoaDTO = new PessoaDTO();

            if (itemEntity.getPessoa() != null){
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
        PessoaDTO pessoaDTO = new PessoaDTO();

        if (itemEntity.getPessoa() != null){
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

    public List<ItemDTO> findAllByPessoa_Cpf(String cpf){
        return itemRepository.findAllByPessoa_Cpf(cpf).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa/Cpf não encontrado");}).stream().map(itemEntity -> {
            ItemDTO itemDTO = new ItemDTO();
            PessoaDTO pessoaDTO = new PessoaDTO();

            if (itemEntity.getPessoa() != null){
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

    public void saveItem(ItemAddDTO input) {
        ItemEntity itemEntity = new ItemEntity();
        PessoaEntity pessoa = new PessoaEntity();

        pessoa.setIdPessoa(input.getPessoaItem().getIdPessoa());
        itemEntity.setPessoa(pessoa);
        itemEntity.setLocalizador(input.getLocalizador());
        itemEntity.setLocalEntrega(input.getLocalEntrega());
        itemEntity.setNomeRecebedor(input.getNomeRecebedor());
        itemEntity.setStatus(input.getStatus());

        itemRepository.save(itemEntity);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public ItemUpDTO updateStatusItem(Long id, String novoStatus) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        ItemUpDTO itemUpDTO = new ItemUpDTO();

        itemUpDTO.setStatus(novoStatus);

        itemEntity.setStatus(novoStatus);
        itemRepository.save(itemEntity);

        return itemUpDTO;
    }

    public ItemUpDTO itemUpDTO(Long id, ItemUpDTO itemDTO) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        itemEntity.setStatus(itemDTO.getStatus());
        itemEntity.setNomeRecebedor(itemDTO.getNomeRecebedor());
        itemEntity.setLocalizador(itemDTO.getLocalizador());
        itemEntity.setLocalEntrega(itemDTO.getLocalEntrega());

        PessoaEntity pessoaDTO = new PessoaEntity();
        pessoaDTO.setIdPessoa(itemEntity.getPessoa().getIdPessoa());
        itemEntity.setPessoa(pessoaDTO);
        itemDTO.setIdItem(itemEntity.getIdItem());
        itemEntity.setPessoa(pessoaRepository.findById(itemDTO.getPessoaItem().getIdPessoa()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        itemEntity = itemRepository.save(itemEntity);
        itemDTO.setIdItem(itemEntity.getIdItem());

        return itemDTO;
    }

}
