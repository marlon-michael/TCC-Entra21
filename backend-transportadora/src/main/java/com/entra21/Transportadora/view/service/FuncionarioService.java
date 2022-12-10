package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.EmpresaDTO;
import com.entra21.Transportadora.model.dto.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    private EmpresaRepository empresaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EntityManager em;

    public FuncionarioDTO findByCpf(String cpf){
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findByCpf(cpf).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário/CPF NOT FOUND");});
        FuncionarioDTO dto = new FuncionarioDTO();
        EmpresaDTO dtoEmp = new EmpresaDTO();
        PessoaDTO dtoPes = new PessoaDTO();
        PessoaDTO dtoPesEmp = new PessoaDTO();

        dtoPesEmp.setNome(funcionarioEntity.getEmpresa().getGerente().getNome());
        dtoPesEmp.setSobrenome(funcionarioEntity.getEmpresa().getGerente().getSobrenome());
        dtoPesEmp.setCpf(funcionarioEntity.getEmpresa().getGerente().getCpf());
        dtoPesEmp.setTelefone(funcionarioEntity.getEmpresa().getGerente().getTelefone());

        dtoEmp.setGerente(dtoPesEmp);
        dtoEmp.setRazaoSocial(funcionarioEntity.getEmpresa().getRazaoSocial());
        dtoEmp.setCnpj(funcionarioEntity.getEmpresa().getCnpj());

        dto.setNome(funcionarioEntity.getNome());
        dto.setSobrenome(funcionarioEntity.getSobrenome());
        dto.setCpf(funcionarioEntity.getCpf());
        dto.setTelefone(funcionarioEntity.getTelefone());
        dto.setEmpresa(dtoEmp);

        if (funcionarioEntity.getSupervisor() == null) {
            return dto;
        } else {
            dtoPes.setNome(funcionarioEntity.getSupervisor().getNome());
            dtoPes.setSobrenome(funcionarioEntity.getSupervisor().getSobrenome());
            dtoPes.setCpf(funcionarioEntity.getSupervisor().getCpf());
            dtoPes.setTelefone(funcionarioEntity.getSupervisor().getTelefone());
            return dto;
        }
    }

    public List<FuncionarioDTO> getAllFuncionario() {
       return funcionarioRepository.findAll().stream().map(funcionarioEntity -> {
           FuncionarioDTO dto = new FuncionarioDTO();
           EmpresaDTO dtoEmp = new EmpresaDTO();
           PessoaDTO dtoPes = new PessoaDTO();
           PessoaDTO dtoPesEmp = new PessoaDTO();
           dto.setNome(funcionarioEntity.getNome());
           dto.setSobrenome(funcionarioEntity.getSobrenome());
           dto.setCpf(funcionarioEntity.getCpf());
           dto.setTelefone(funcionarioEntity.getTelefone());
           dtoEmp.setRazaoSocial(funcionarioEntity.getEmpresa().getRazaoSocial());
           dtoEmp.setCnpj(funcionarioEntity.getEmpresa().getCnpj());
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
               return dto;
           }
       }).collect(Collectors.toList());
    }

    public List<FuncionarioDTO> getAllFuncionariByEmpresa(String cnpj){
        return funcionarioRepository.findAllByEmpresa_Cnpj(cnpj).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EMPRESA/CNPJ/FUNCIONARIO NOT FOUND");}).stream().map(funcionarioEntity -> {
            FuncionarioDTO dto = new FuncionarioDTO();
            EmpresaDTO dtoEmp = new EmpresaDTO();
            PessoaDTO dtoPes = new PessoaDTO();
            PessoaDTO dtoPesEmp = new PessoaDTO();

            dto.setNome(funcionarioEntity.getNome());
            dto.setSobrenome(funcionarioEntity.getSobrenome());
            dto.setCpf(funcionarioEntity.getCpf());
            dto.setTelefone(funcionarioEntity.getTelefone());
            dtoEmp.setRazaoSocial(funcionarioEntity.getEmpresa().getRazaoSocial());
            dtoEmp.setCnpj(funcionarioEntity.getEmpresa().getCnpj());
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
                return dto;
            }
        }).collect(Collectors.toList());
    }

    @Transactional
    public void saveFuncionario(FuncionarioDTO input) {
        Query q = em.createNativeQuery("INSERT INTO funcionario(id_pessoa, id_empresa, id_supervisor) VALUES(:idPessoa, :idEmpresa, :idSupervisor)");
        
        PessoaEntity pessoaEntity = pessoaRepository.findByCpf(input.getCpf()).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF NOT FOUND");});
        if (pessoaEntity.getNome().toLowerCase().strip().equals(input.getNome().toLowerCase().strip()) &&
            pessoaEntity.getSobrenome().toLowerCase().strip().equals(input.getSobrenome().toLowerCase().strip()) &&
            pessoaEntity.getTelefone().strip().equals(input.getTelefone().strip() ) 
        ){
            q.setParameter("idPessoa",pessoaEntity.getIdPessoa());
            q.setParameter("idEmpresa",
                empresaRepository.findByCnpj(input.getEmpresa().getCnpj()).orElseThrow(() -> 
                {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CNPJ NOT FOUND");}).getIdEmpresa());
            if (input.getSupervisor() != null){
                q.setParameter("idSupervisor",
                funcionarioRepository.findByCpf(input.getSupervisor().getCpf()).orElseThrow(() -> 
                {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF NOT FOUND");}).getIdPessoa());
            }
            else q.setParameter("idSupervisor",null);
            q.executeUpdate();  
        }
        else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "DATA NOT MATCH");
        }
    }

    public void deleteByFuncionario(String cpf){
        funcionarioRepository.deleteById(funcionarioRepository.findByCpf(cpf).orElseThrow(() -> {throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Cpf/Funcionário not found");}).getIdPessoa());
    }


}

