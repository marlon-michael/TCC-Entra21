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

    public PessoaDTO buscarUsuarioLogado() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        PessoaEntity user = new PessoaEntity();
        try {
            user = (PessoaEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        pessoaDTO.setCpf(user.getCpf());
        pessoaDTO.setNome(user.getNome());
        pessoaDTO.setSobrenome(user.getSobrenome());
        pessoaDTO.setTelefone(user.getTelefone());
        pessoaDTO.setLogin(user.getLogin());
        pessoaDTO.setSenha(user.getSenha());
        pessoaDTO.setDesabilitado(user.getDesabilitado());
        pessoaDTO.setIdPessoa(user.getIdPessoa());

        return pessoaDTO;
    }

    public PessoaDTO findByCpf(String cpf){
        PessoaEntity pessoaEntity = pessoaRepository.findByCpf(cpf).orElseThrow(
            () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");});
        PessoaDTO dto = new PessoaDTO();
        dto.setNome(pessoaEntity.getNome());
        dto.setSobrenome(pessoaEntity.getSobrenome());
        dto.setCpf(pessoaEntity.getCpf());
        dto.setTelefone(pessoaEntity.getTelefone());
        return dto;
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

    // PRA QUE SERVE ???
    public List<PessoaDTO> getAllByFuncionario() {
        return pessoaRepository.findAll().stream().map(pr -> {
            PessoaDTO pessoaDTO = new PessoaDTO();
            pessoaDTO.setNome(pr.getNome());
            pessoaDTO.setSobrenome(pr.getSobrenome());
            pessoaDTO.setCpf(pr.getCpf());
            pessoaDTO.setTelefone(pr.getTelefone());
            pessoaDTO.setLogin(pr.getLogin());
            pessoaDTO.setSenha(pr.getSenha());
            pessoaDTO.setDesabilitado(pr.getDesabilitado());
            return pessoaDTO;
        }).collect(Collectors.toList());
    }


    public void save(PessoaAddDTO input) {
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setNome(input.getNome());
        pessoaEntity.setSobrenome(input.getSobrenome());
        pessoaEntity.setTelefone(input.getTelefone());
        pessoaEntity.setCpf(input.getCpf());
        pessoaEntity.setLogin(input.getLogin());
        pessoaEntity.setSenha(input.getSenha());
        pessoaEntity.setDesabilitado(false);
        pessoaRepository.save(pessoaEntity);
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
