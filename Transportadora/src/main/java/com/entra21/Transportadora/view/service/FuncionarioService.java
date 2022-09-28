package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.*;
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

    public List<FuncionarioPayLoadDTO>getAllFuncionario() {
        return funcionarioRepository.findAll().stream().map(fr -> {
            FuncionarioPayLoadDTO dto = new FuncionarioPayLoadDTO();
            GetAllEmpresasDTO dtoEmp = new GetAllEmpresasDTO();
            PessoaDTO dtoPes = new PessoaDTO();
            PessoaDTO dtoPesEmp = new PessoaDTO();
            dto.setNome(fr.getNome());
            dto.setSobrenome(fr.getSobrenome());
            dto.setCpf(fr.getCpf());
            dto.setTelefone(fr.getTelefone());
            dto.setIdFuncionario(fr.getIdPessoa());

            dtoEmp.setRazaoSocial(fr.getEmpresa().getRazaoSocial());
            dtoEmp.setIdEmpresa(fr.getEmpresa().getIdEmpresa());


            dtoPesEmp.setNome(fr.getEmpresa().getGerente().getNome());
            dtoPesEmp.setSobrenome(fr.getEmpresa().getGerente().getSobrenome());
            dtoPesEmp.setCpf(fr.getEmpresa().getGerente().getCpf());
            dtoPesEmp.setTelefone(fr.getEmpresa().getGerente().getTelefone());
            dtoPesEmp.setIdPessoa(fr.getEmpresa().getGerente().getIdPessoa());

            dtoEmp.setNomeGerente(dtoPesEmp);
            dto.setEmpresaFuncionario(dtoEmp);

            if (fr.getSupervisor() == null) {
                return dto;
            } else {
                dtoPes.setNome(fr.getSupervisor().getNome());
                dtoPes.setSobrenome(fr.getSupervisor().getSobrenome());
                dtoPes.setCpf(fr.getSupervisor().getCpf());
                dtoPes.setTelefone(fr.getSupervisor().getTelefone());
                dtoPes.setIdPessoa(fr.getSupervisor().getIdPessoa());
                dto.setSupervisorFuncionario(dtoPes);
                return dto;
            }
        }).collect(Collectors.toList());
    }

    @Transactional
    public void saveFuncionario(FuncionarioDTO input) {
        Query q = em.createNativeQuery("INSERT INTO funcionario(id_pessoa, id_empresa, id_supervisor) VALUES(:idPessoa, :idEmpresa, :idSupervisor)");
        q.setParameter("idPessoa", input.getIdPessoa());
        q.setParameter("idEmpresa", input.getIdEmpresa());
        q.setParameter("idSupervisor", input.getIdSupervisor());
        q.executeUpdate();
    }


}