package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaAddDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaUpDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
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
        EmpresaEntity empresaEntity = new EmpresaEntity();
        empresaEntity.setCnpj(inputEmpresa.getCnpj());
        empresaEntity.setRazaoSocial(inputEmpresa.getRazaoSocial());
        empresaEntity.setGerente(pessoaRepository.findByCpf(inputEmpresa.getGerente().getCpf()).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa/CPF não foi encontrado!");
        }));

        empresaRepository.save(empresaEntity);
    }

    public void deleteEmpresa (String cnpj){
        empresaRepository.deleteById(
                empresaRepository.findByCnpj(cnpj).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa/CNPJ não encontrado");}).getIdEmpresa()
        );
    }

    public List<EmpresaDTO> getAllEmpresas () {

        return empresaRepository.findAll().stream().map(empresaEntity -> {

            PessoaDTO pessoaDTO = new PessoaDTO();
            EmpresaDTO dtoempresa = new EmpresaDTO();

            pessoaDTO .setNome(empresaEntity.getGerente().getNome());
            pessoaDTO .setCpf(empresaEntity.getGerente().getCpf());
            pessoaDTO .setTelefone(empresaEntity.getGerente().getTelefone());
            pessoaDTO .setSobrenome(empresaEntity.getGerente().getSobrenome());

            dtoempresa.setGerente(pessoaDTO);
            dtoempresa.setCnpj(empresaEntity.getCnpj());
            dtoempresa.setRazaoSocial(empresaEntity.getRazaoSocial());

            return dtoempresa;
        }).collect(Collectors.toList());
    }

    public EmpresaDTO findByCnpj(String cnpj){
        EmpresaEntity empresaEntity = empresaRepository.findByCnpj(cnpj).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa/CNPJ não encontrada");});
        PessoaDTO pessoaDTO = new PessoaDTO();
        EmpresaDTO empresaDTO = new EmpresaDTO();

        pessoaDTO .setNome(empresaEntity.getGerente().getNome());
        pessoaDTO .setCpf(empresaEntity.getGerente().getCpf());
        pessoaDTO .setTelefone(empresaEntity.getGerente().getTelefone());
        pessoaDTO .setSobrenome(empresaEntity.getGerente().getSobrenome());

        empresaDTO.setGerente(pessoaDTO);
        empresaDTO.setCnpj(empresaEntity.getCnpj());
        empresaDTO.setRazaoSocial(empresaEntity.getRazaoSocial());

        return empresaDTO;
    }


    public void updateEmpresa (String cnpj, EmpresaUpDTO empresaDTO){
        EmpresaEntity empresaEntity = empresaRepository.findByCnpj(cnpj).orElseThrow(() -> new ResponseStatusException(HttpStatus.OK, "Empresa/CNPJ não encontrado!"));
        empresaEntity.setCnpj(empresaDTO.getCnpj());
        empresaEntity.setRazaoSocial(empresaDTO.getRazaoSocial());

        empresaEntity.setGerente(pessoaRepository.findByCpf(empresaDTO.getGerente().getCpf()).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa/CPF não foi encontrado!");
        }));

        empresaRepository.save(empresaEntity);
    }
}