package com.entra21.Transportadora.view.service;


import com.entra21.Transportadora.model.dto.*;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import com.entra21.Transportadora.model.dto.EmpresaDTO;
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

        public void saveEmpresas(EmpresaAddDTO inputEmpresa) {
            //newEntity.setIdEmpresa(inputEmpresa.getIdEmpresa());
            pessoaRepository.findById(inputEmpresa.getIdGerente()).ifPresentOrElse(pE -> {
                EmpresaEntity newEntity = new EmpresaEntity();
                newEntity.setRazaoSocial(inputEmpresa.getRazaoSocial());
                newEntity.setGerente(pE);
                empresaRepository.save(newEntity);
            }, () -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gerente não foi encontrado!");
            });
        }

        public void deleteEmpresa(Long idEmpresa) {
            empresaRepository.deleteById(idEmpresa);
        }

        public List<GetAllEmpresasDTO> getAllEmpresas() {
            return empresaRepository.findAll().stream().map(er -> {
                GetAllEmpresasDTO dtoempresa = new GetAllEmpresasDTO();
                dtoempresa.setIdEmpresa(er.getIdEmpresa());
                dtoempresa.setRazaoSocial(er.getRazaoSocial());
//                dtoempresa.setNomeGerente(er.getGerente().getNome());

                PessoaDTO cr2 = new PessoaDTO();
        cr2.setNome(er.getGerente().getNome());
        cr2.setCpf(er.getGerente().getCpf());
        cr2.setTelefone(er.getGerente().getTelefone());
        cr2.setSobrenome(er.getGerente().getSobrenome());
dtoempresa.setNomeGerente(cr2);
                return dtoempresa;
            }).collect(Collectors.toList());
        }







        public EmpresaDTO updateEmpresa(Long idEmpresanv, EmpresaAddDTO empresaAddDTO) {
            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaRepository.findById(idEmpresanv).ifPresentOrElse(eE -> {
                pessoaRepository.findById(empresaAddDTO.getIdGerente()).ifPresentOrElse(pE -> {
                    eE.setRazaoSocial(empresaAddDTO.getRazaoSocial());
                    eE.setGerente(pE);
                    empresaDTO.setRazaoSocial(eE.getRazaoSocial());
                    empresaRepository.save(eE);
                }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gerente não foi encontrado!");});
            }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa não foi encontrado!");});
//            e.setIdEmpresa(empresaAddDTO.getIdEmpresa());;
            return empresaDTO;
        }

}
