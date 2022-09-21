package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.CarroDTO;
import com.entra21.Transportadora.model.dto.EmpresaDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmpresaService {

        @Autowired
        private EmpresaRepository empresaRepository;

        public void saveEmpresas(EmpresaDTO inputEmpresa) {
            EmpresaEntity newEntity = new EmpresaEntity();
            newEntity.setIdEmpresa(inputEmpresa.getIdEmpresa());
            newEntity.setRazaoSocial(inputEmpresa.getRazaoSocial());
            newEntity.setIdGerente(inputEmpresa.getGerente());
            empresaRepository.save(newEntity);
        }

        public void deleteEmpresa(Long idEmpresa) {
            empresaRepository.deleteById(idEmpresa);
        }

        public List<EmpresaDTO> getAllEmpresas() {
            return empresaRepository.findAll().stream().map(er -> {
                EmpresaDTO dtoempresa = new EmpresaDTO();
                dtoempresa.setIdEmpresa(er.getIdEmpresa());
                dtoempresa.setRazaoSocial(er.getRazaoSocial());
                dtoempresa.setGerente(er.getIdGerente());
                return dtoempresa;
            }).collect(Collectors.toList());
        }


        public EmpresaDTO updateEmpresa(Long idEmpresanv, EmpresaDTO empresaDTO) {
            EmpresaEntity e = empresaRepository.findById(idEmpresanv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada!"));
            e.setIdEmpresa(empresaDTO.getIdEmpresa());
            e.setRazaoSocial(empresaDTO.getRazaoSocial());
            e.setIdGerente(empresaDTO.getGerente());
            e = empresaRepository.save(e);
            empresaDTO.setIdEmpresa(e.getIdEmpresa());
            return empresaDTO;
        }

}
