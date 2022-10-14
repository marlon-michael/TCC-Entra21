package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;

import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioUpDTO;
import com.entra21.Transportadora.model.dto.Item.ItemUpDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
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

    public List<FuncionarioDTO> getAllFuncionario() {
       return funcionarioRepository.findAll().stream().map(fr -> {
           FuncionarioDTO dto = new FuncionarioDTO();
           EmpresaDTO dtoEmp = new EmpresaDTO();
           PessoaDTO dtoPes = new PessoaDTO();
           PessoaDTO dtoPesEmp = new PessoaDTO();
           dto.setNome(fr.getNome());
           dto.setSobrenome(fr.getSobrenome());
           dto.setCpf(fr.getCpf());
           dto.setTelefone(fr.getTelefone());
           dtoEmp.setRazaoSocial(fr.getEmpresa().getRazaoSocial());
           dtoPesEmp.setNome(fr.getEmpresa().getGerente().getNome());
           dtoPesEmp.setSobrenome(fr.getEmpresa().getGerente().getSobrenome());
           dtoPesEmp.setCpf(fr.getEmpresa().getGerente().getCpf());
           dtoPesEmp.setTelefone(fr.getEmpresa().getGerente().getTelefone());
           dtoEmp.setGerente(dtoPesEmp);
           dto.setEmpresa(dtoEmp);
           if (fr.getSupervisor() == null) {
               return dto;
           } else {
               dtoPes.setNome(fr.getSupervisor().getNome());
               dtoPes.setSobrenome(fr.getSupervisor().getSobrenome());
               dtoPes.setCpf(fr.getSupervisor().getCpf());
               dtoPes.setTelefone(fr.getSupervisor().getTelefone());
               //erro ARRUMAR
               return dto;
           }
       }).collect(Collectors.toList());
    }
//TESTAR - CASA
    public List<FuncionarioDTO> getAllFuncionarioById() {
        return funcionarioRepository.findAll().stream().map(fr -> {
            FuncionarioDTO dto = new FuncionarioDTO();
            dto.setNome(fr.getNome());
            dto.setSobrenome(fr.getSobrenome());
            dto.setCpf(fr.getCpf());
            dto.setTelefone(fr.getTelefone());
            PessoaDTO dtoPes = new PessoaDTO();
            PessoaDTO dtoPesEmp = new PessoaDTO();
            if (fr.getSupervisor() == null) {
                return dto;
            } else {
                dtoPes.setNome(fr.getSupervisor().getNome());
                dtoPes.setSobrenome(fr.getSupervisor().getSobrenome());
                dtoPes.setCpf(fr.getSupervisor().getCpf());
                dtoPes.setTelefone(fr.getSupervisor().getTelefone());
            fr.getEmpresa().getFuncionarios().stream().map(EmpresaEntity -> {

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



