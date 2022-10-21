package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;
<<<<<<< HEAD
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioUpDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
=======
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
>>>>>>> 2c5ebfca49f4067c9735a65bb9213265c35ff608
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
    public void saveFuncionario(FuncionarioAddDTO input) {
        Query q = em.createNativeQuery("INSERT INTO funcionario(id_pessoa, id_empresa, id_supervisor) VALUES(:idPessoa, :idEmpresa, :idSupervisor)");
        
        q.setParameter("idPessoa",
            pessoaRepository.findByCpf(input.getCpf()).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF NOT FOUND");}).getIdPessoa()
        );
        q.setParameter("idEmpresa",
            empresaRepository.findByCnpj(input.getEmpresa().getCnpj()).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CNPJ NOT FOUND");}).getIdEmpresa()
        );
        q.setParameter("idSupervisor",
            funcionarioRepository.findByCpf(input.getSupervisor().getCpf()).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF NOT FOUND");}).getIdPessoa()
        );

        q.executeUpdate();  
    }

    public void deleteByFuncionario(String cpf){
        funcionarioRepository.deleteById(funcionarioRepository.findByCpf(cpf).orElseThrow(() -> {throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Cpf/Funcionário not found");}).getIdPessoa());
    }
    
    public FuncionarioUpDTO funcionarioUpDTO(Long id, FuncionarioUpDTO novoFuncionario){
        FuncionarioEntity e = funcionarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado!"));
        e.setIdPessoa(novoFuncionario.getIdPessoa());



        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setCnpj(novoFuncionario.getEmpresa().getCnpj());

        //Supervisor
        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setIdPessoa(novoFuncionario.getIdPessoa());


        e.setIdPessoa(novoFuncionario.getIdPessoa());
        e.setEmpresa(empresa);
        e.setSupervisor(funcionario);

        return novoFuncionario;
    }


}



