package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;

import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EntityManager em;

    public List<FuncionarioDTO> getAllFuncionario() {
       return funcionarioRepository.findAll().stream().map(funcionarioEntity
        -> {
           FuncionarioDTO dto = new FuncionarioDTO();
           EmpresaDTO dtoEmp = new EmpresaDTO();
           PessoaDTO dtoPes = new PessoaDTO();
           PessoaDTO dtoPesEmp = new PessoaDTO();
           dto.setNome(funcionarioEntity.getNome());
           dto.setSobrenome(funcionarioEntity.getSobrenome());
           dto.setCpf(funcionarioEntity.getCpf());
           dto.setTelefone(funcionarioEntity.getTelefone());
           dtoEmp.setRazaoSocial(funcionarioEntity.getEmpresa().getRazaoSocial());
           dtoPesEmp.setNome(funcionarioEntity.getEmpresa().getGerente().getNome());
           dtoPesEmp.setSobrenome(funcionarioEntity.getEmpresa().getGerente().getSobrenome());
           dtoPesEmp.setCpf(funcionarioEntity.getEmpresa().getGerente().getCpf());
           dtoPesEmp.setTelefone(funcionarioEntity.getEmpresa().getGerente().getTelefone());
           dtoEmp.setGerente(dtoPesEmp);
           dto.setEmpresa(dtoEmp);
           if (funcionarioEntity.getSupervisor() == null) {
               return dto;
           } else {
               dtoPes.setNome(funcionarioEntity.getSupervisor().getNome());
               dtoPes.setSobrenome(funcionarioEntity.getSupervisor().getSobrenome());
               dtoPes.setCpf(funcionarioEntity.getSupervisor().getCpf());
               dtoPes.setTelefone(funcionarioEntity.getSupervisor().getTelefone());
               //erro ARRUMAR
               return dto;
           }
       }).collect(Collectors.toList());
    }
//TESTAR - CASA
    public List<FuncionarioDTO> getAllFuncionarioById() {
        return funcionarioRepository.findAll().stream().map(funcionarioEntity -> {
            FuncionarioDTO dto = new FuncionarioDTO();
            dto.setNome(funcionarioEntity.getNome());
            dto.setSobrenome(funcionarioEntity.getSobrenome());
            dto.setCpf(funcionarioEntity.getCpf());
            dto.setTelefone(funcionarioEntity.getTelefone());
            PessoaDTO dtoPes = new PessoaDTO();
            PessoaDTO dtoPesEmp = new PessoaDTO();
            if (funcionarioEntity.getSupervisor() == null) {
                return dto;
            } else {
                dtoPes.setNome(funcionarioEntity.getSupervisor().getNome());
                dtoPes.setSobrenome(funcionarioEntity.getSupervisor().getSobrenome());
                dtoPes.setCpf(funcionarioEntity.getSupervisor().getCpf());
                dtoPes.setTelefone(funcionarioEntity.getSupervisor().getTelefone());
            funcionarioEntity.getEmpresa().getFuncionarios().stream().map(EmpresaEntity -> {

                    //erro ARRUMAR
                    EmpresaDTO empresaDTO = new EmpresaDTO();
                    empresaDTO.setFuncionarios(dto.getEmpresa().getFuncionarios());
                    return empresaDTO;
                }).collect(Collectors.toList());
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void saveFuncionario(FuncionarioAddDTO input) {
        Query q = em.createNativeQuery("INSERT INTO funcionario(id_pessoa, id_empresa, id_supervisor) VALUES(:idPessoa, :idEmpresa, :idSupervisor)");
        q.setParameter("idPessoa", input.getIdPessoa());
        q.setParameter("idEmpresa", input.getEmpresa().getId());
        q.setParameter("idSupervisor", input.getSupervisor().getIdPessoa());
        q.executeUpdate();
    }

}

