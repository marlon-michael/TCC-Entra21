package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaAddDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public void saveEmpresas (EmpresaAddDTO inputEmpresa){
        EmpresaEntity newEntity = new EmpresaEntity();
        PessoaEntity pessoaDTO = new PessoaEntity();
        newEntity.setIdEmpresa(inputEmpresa.getIdEmpresa());
        newEntity.setRazaoSocial(inputEmpresa.getRazaoSocial());
        newEntity.setGerente(pessoaRepository.findByLogin(inputEmpresa.getGerente().getNome()));

        empresaRepository.save(newEntity);
    }

    public void deleteEmpresa (Long idEmpresa){
        empresaRepository.deleteById(idEmpresa);
    }

    public List<GetAllEmpresasDTO> getAllEmpresas () {
        return empresaRepository.findAll().stream().map(er -> {
            GetAllEmpresasDTO dtoempresa = new GetAllEmpresasDTO();
            dtoempresa.setIdEmpresa(er.getIdEmpresa());
            dtoempresa.setRazaoSocial(er.getRazaoSocial());
//          dtoempresa.setNomeGerente(er.getGerente().getNome());

            PessoaDTO cr2 = new PessoaDTO();
            cr2.setNome(er.getGerente().getNome());
            cr2.setCpf(er.getGerente().getCpf());
            cr2.setTelefone(er.getGerente().getTelefone());
            cr2.setSobrenome(er.getGerente().getSobrenome());

            dtoempresa.setNomeGerente(cr2);
            return dtoempresa;
        }).collect(Collectors.toList());
    }

    public List<EmpresaDTO> getAllEmpresasgerente () {
        return empresaRepository.findAll().stream().map(er -> {
            EmpresaDTO dtoempresa = new EmpresaDTO();
            PessoaDTO pessoaDTO = new PessoaDTO();
            dtoempresa.setRazaoSocial(er.getRazaoSocial());
            pessoaDTO.setNome(er.getGerente().getNome());
            dtoempresa.setGerente(pessoaDTO);
            pessoaDTO.setSobrenome(dtoempresa.getGerente().getSobrenome());
            pessoaDTO.setTelefone(dtoempresa.getGerente().getTelefone());
            pessoaDTO.setCpf(dtoempresa.getGerente().getCpf());
//                pessoaDTO.setNome(pessoaDTO.getNome());
            return dtoempresa;
        }).collect(Collectors.toList());
    }


    public EmpresaAddDTO updateEmpresa (Long idEmpresanv, EmpresaAddDTO empresaDTO){
        EmpresaEntity e = empresaRepository.findById(idEmpresanv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada!"));
        PessoaAddDTO pessoaDTO = new PessoaAddDTO();
        e.setIdEmpresa(empresaDTO.getIdEmpresa());
        e.setRazaoSocial(empresaDTO.getRazaoSocial());
        pessoaDTO.setIdPessoa(empresaDTO.getGerente().getIdPessoa());
        e = empresaRepository.save(e);
        empresaDTO.setGerente(pessoaDTO);
        empresaDTO.setIdEmpresa(e.getIdEmpresa());
        return empresaDTO;
    }
}