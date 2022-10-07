package com.entra21.Transportadora.view.service;


import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaUpDTO;
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
public class PessoaService implements UserDetailsService{

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PessoaEntity user = pessoaRepository.findByLogin(username).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não foi encontrada!");
        });
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
            dto.setNome(pr.getNome());
            dto.setSobrenome(pr.getSobrenome());
            dto.setCpf(pr.getCpf());
            dto.setTelefone(pr.getTelefone());
            return dto;
        }).collect(Collectors.toList());
    }

    public List<PessoaDTO> getAllByFuncionario() {
        return pessoaRepository.findAll().stream().map(pr -> {
            PessoaDTO dtoN = new PessoaDTO();
            dtoN.setNome(pr.getNome());
            dtoN.setSobrenome(pr.getSobrenome());
            dtoN.setCpf(pr.getCpf());
            dtoN.setTelefone(pr.getTelefone());
            dtoN.setLogin(pr.getLogin());
            dtoN.setSenha(pr.getSenha());
            dtoN.setDesabilitado(pr.getDesabilitado());
            return dtoN;
        }).collect(Collectors.toList());
    }

    public void save(PessoaAddDTO input) {
        PessoaEntity newEntity = new PessoaEntity();
        newEntity.setNome(input.getNome());
        newEntity.setSobrenome(input.getSobrenome());
        newEntity.setTelefone(input.getTelefone());
        newEntity.setCpf(input.getCpf());
        newEntity.setLogin(input.getLogin());
        newEntity.setSenha(input.getSenha());
        newEntity.setDesabilitado(input.getDesabilitado());
        pessoaRepository.save(newEntity);
    }

    public PessoaUpDTO updatePessoa(Long id, PessoaUpDTO pessoaPayLoadDTO) {
        PessoaEntity pessoa = pessoaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!"));
        pessoa.setNome(pessoaPayLoadDTO.getNome());
        pessoa.setSobrenome(pessoaPayLoadDTO.getSobrenome());
        pessoa.setTelefone(pessoaPayLoadDTO.getTelefone());
        pessoa.setCpf(pessoaPayLoadDTO.getCpf());
        pessoa.setLogin(pessoaPayLoadDTO.getLogin());
        pessoa.setSenha(pessoaPayLoadDTO.getSenha());
        pessoa.setDesabilitado(pessoaPayLoadDTO.getDesabilitado());
        pessoa = pessoaRepository.save(pessoa);
        PessoaAddDTO dto = new PessoaAddDTO();
        dto.setNome(pessoa.getNome());
        dto.setSobrenome(pessoa.getSobrenome());
        dto.setTelefone(pessoa.getTelefone());
        dto.setCpf(pessoa.getCpf());
        dto.setLogin(pessoa.getLogin());
        dto.setSenha(pessoa.getSenha());
        dto.setDesabilitado(pessoa.getDesabilitado());
        return pessoaPayLoadDTO;
    }

}
