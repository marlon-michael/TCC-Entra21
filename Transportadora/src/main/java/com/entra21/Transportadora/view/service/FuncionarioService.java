package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
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
    //                   dto.setLogin(fr.getLogin());
    //                   dto.setSenha(fr.getSenha());
           dtoEmp.setRazaoSocial(fr.getEmpresa().getRazaoSocial());
           dtoPesEmp.setNome(fr.getEmpresa().getGerente().getNome());
           dtoPesEmp.setSobrenome(fr.getEmpresa().getGerente().getSobrenome());
           dtoPesEmp.setCpf(fr.getEmpresa().getGerente().getCpf());
           dtoPesEmp.setTelefone(fr.getEmpresa().getGerente().getTelefone());
           dtoEmp.setGerente(dtoPesEmp);

           dto.setEmpresaFuncionario(dtoEmp);
           if (fr.getSupervisor() == null) {
               return dto;
           } else {
               dtoPes.setNome(fr.getSupervisor().getNome());
               dtoPes.setSobrenome(fr.getSupervisor().getSobrenome());
               dtoPes.setCpf(fr.getSupervisor().getCpf());
               dtoPes.setTelefone(fr.getSupervisor().getTelefone());
               dto.setSupervisorFuncionario(dtoPes);
               return dto;
           }
       }).collect(Collectors.toList());
    }

    @Transactional
    public void saveFuncionario(FuncionarioAddDTO input) {
        Query q = em.createNativeQuery("INSERT INTO funcionario(id_pessoa, id_empresa, id_supervisor) VALUES(:idPessoa, :idEmpresa, :idSupervisor)");
        q.setParameter("idPessoa", input.getIdPessoa());
        q.setParameter("idEmpresa", input.getIdEmpresa());
        q.setParameter("idSupervisor", input.getIdSupervisor());
        q.executeUpdate();
    }

}

