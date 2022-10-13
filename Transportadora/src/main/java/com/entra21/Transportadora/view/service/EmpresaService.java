package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaAddDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaUpDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaUpDTO;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
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
        newEntity.setIdEmpresa(inputEmpresa.getId());
        newEntity.setRazaoSocial(inputEmpresa.getRazaoSocial());
        newEntity.setGerente(pessoaRepository.findByLogin(inputEmpresa.getGerente().getNome()).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não foi encontrada!");
        }));

        empresaRepository.save(newEntity);
    }

    public void deleteEmpresa (Long idEmpresa){
        empresaRepository.deleteById(idEmpresa);
    }

    public List<EmpresaDTO> getAllEmpresas () {
        return empresaRepository.findAll().stream().map(er -> {
            EmpresaDTO dtoempresa = new EmpresaDTO();
            dtoempresa.setId(er.getIdEmpresa());
            dtoempresa.setCnpj(er.getCnpj());
            dtoempresa.setRazaoSocial(er.getRazaoSocial());

            PessoaDTO pessoaDTO = new PessoaDTO();
            pessoaDTO .setNome(er.getGerente().getNome());
            pessoaDTO .setCpf(er.getGerente().getCpf());
            pessoaDTO .setTelefone(er.getGerente().getTelefone());
            pessoaDTO .setSobrenome(er.getGerente().getSobrenome());

            dtoempresa.setGerente(pessoaDTO);
            return dtoempresa;
        }).collect(Collectors.toList());
    }

    public List<EmpresaEntity> findByCnpj(String cnpj){
        return empresaRepository.findByCnpj(cnpj).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada");});
    }

    public List<EmpresaDTO> getAllEmpresasgerente () {
        return empresaRepository.findAll().stream().map(er -> {
            EmpresaDTO dtoempresa = new EmpresaDTO();
            PessoaDTO pessoaDTO = new PessoaDTO();

            pessoaDTO.setNome(er.getGerente().getNome());
            pessoaDTO.setSobrenome(dtoempresa.getGerente().getSobrenome());
            pessoaDTO.setTelefone(dtoempresa.getGerente().getTelefone());
            pessoaDTO.setCpf(dtoempresa.getGerente().getCpf());

            dtoempresa.setCnpj(er.getCnpj());
            dtoempresa.setRazaoSocial(er.getRazaoSocial());
            dtoempresa.setGerente(pessoaDTO);
            return dtoempresa;
        }).collect(Collectors.toList());
    }


    public EmpresaUpDTO updateEmpresa (Long idEmpresanv, EmpresaUpDTO empresaDTO){
        EmpresaEntity empresa = empresaRepository.findById(idEmpresanv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada!"));
        PessoaUpDTO pessoaDTO = new PessoaUpDTO();
        empresa.setIdEmpresa(empresaDTO.getId());
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setRazaoSocial(empresaDTO.getRazaoSocial());
        pessoaDTO.setIdPessoa(empresaDTO.getGerente().getIdPessoa());
        empresa = empresaRepository.save(empresa);
        empresaDTO.setGerente(pessoaDTO);
        empresaDTO.setId(empresa.getIdEmpresa());
        return empresaDTO;
    }
}