package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.ItemDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import com.entra21.Transportadora.model.entity.ItemEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

   @Autowired
   private FuncionarioRepository funcionarioRepository;

   @Autowired
   private EmpresaRepository empresaRepository;

   @Autowired
   private PessoaRepository pessoaRepository;

    public List<FuncionarioDTO> getAllFuncionario() {
       return funcionarioRepository.findAll().stream().map(fr -> {
           FuncionarioDTO dto = new FuncionarioDTO();
           dto.setNome(fr.getNome());
           dto.setSobrenome(fr.getSobrenome());
           dto.setCpf(fr.getCpf());
           dto.setTelefone(fr.getTelefone());
           dto.setLogin(fr.getLogin());
           dto.setSenha(fr.getSenha());
           dto.setEmpresaFuncionario(fr.getEmpresa().getIdEmpresa());
           if (fr.getSupervisor() == null){
               return dto;
           }else{
               dto.setSupervisorFuncionario(fr.getSupervisor().getIdPessoa());
               return dto;
           }
       }).collect(Collectors.toList());
   }

    public void saveFuncionario(FuncionarioDTO input) {
        FuncionarioEntity newEntity = new FuncionarioEntity();
        newEntity.setNome(input.getNome());
        newEntity.setSobrenome(input.getSobrenome());
        newEntity.setCpf(input.getCpf());
        newEntity.setTelefone(input.getTelefone());
        newEntity.setDesabilitado(false);
        newEntity.setLogin(input.getLogin());
        newEntity.setSenha(input.getSenha());

    newEntity.setEmpresa(empresaRepository.findById(input.getEmpresaFuncionario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    newEntity.setSupervisor(funcionarioRepository.findById(input.getSupervisorFuncionario()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)));
        funcionarioRepository.save(newEntity);
    }
}
