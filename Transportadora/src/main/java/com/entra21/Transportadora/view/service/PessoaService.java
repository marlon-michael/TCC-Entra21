package com.entra21.Transportadora.view.service;


import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PessoaEntity user = pessoaRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public PessoaEntity buscarUsuarioLogado() {
        try {
            return (PessoaEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }


    public List<PessoaDTO> getAll() {
        return pessoaRepository.findAll().stream().map(pr -> {
            PessoaDTO dto = new PessoaDTO();
            dto.setIdPessoa(pr.getIdPessoa());
            dto.setTelefone(pr.getTelefone());
            dto.setSobrenome(pr.getSobrenome());
            dto.setNome(pr.getNome());
            dto.setCpf(pr.getCpf());
            dto.setLogin(pr.getLogin());
            dto.setSenha(pr.getSenha());
            return dto;
        }).collect(Collectors.toList());
    }

    public void save(PessoaDTO input) {
        PessoaEntity newEntity = new PessoaEntity();
        newEntity.setIdPessoa(input.getIdPessoa());
        newEntity.setNome(input.getNome());
        newEntity.setSobrenome(input.getSobrenome());
        newEntity.setTelefone(input.getTelefone());
        newEntity.setCpf(input.getCpf());
        newEntity.setLogin(input.getLogin());
        newEntity.setSenha(input.getSenha());
        pessoaRepository.save(newEntity);
    }

//
//    private String cpf;
//    private String login;
//    private  String senha;

//    public void delete(Long id) {
//        pessoaRepository.deleteById(id);
//    }

    public PessoaDTO updatePessoa(Long id,String novoNome, String novoSobrenome, String novoTelefone,  String novoCPF,  String novoLogin,  String novoSenha) {
        PessoaEntity e = pessoaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"));
        e.setNome(novoNome);
        e.setSobrenome(novoSobrenome);
        e.setTelefone(novoTelefone);
        e.setCpf(novoCPF);
        e.setLogin(novoLogin);
        e.setSenha(novoSenha);
        e = pessoaRepository.save(e);
        PessoaDTO dto = new PessoaDTO();
        dto.setNome(e.getNome());
        dto.setSobrenome(e.getSobrenome());
        dto.setTelefone(e.getTelefone());
       dto.setIdPessoa(e.getIdPessoa());
        e.setCpf(novoCPF);
        e.setLogin(novoLogin);
        e.setSenha(novoSenha);
        return dto;
    }

//
//
//    public void save(FranquiaPayloadDTO input) {
//        FranquiaEntity newEntity = new FranquiaEntity();
//        newEntity.setNome(input.getNome());
//        franquiaRepository.save(newEntity);
//    }
//
//    public FranquiaDTO getById(Long id) {
//        FranquiaEntity e = franquiaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquia não encontrada!"));
//        FranquiaDTO dto = new FranquiaDTO();
//        dto.setId(e.getId());
//        dto.setNome(e.getNome());
//        return dto;
//    }
//
//
//
//
}
