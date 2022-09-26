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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {
//
//   @Autowired
//   private FuncionarioRepository funcionarioRepository;
//
//   @Autowired
//   private EmpresaRepository empresaRepository;
//
//   @Autowired
//   private PessoaRepository pessoaRepository;
//
//    public List<FuncionarioDTO> getAllFuncionario() {
//       return funcionarioRepository.findAll().stream().map(fr -> {
//                   FuncionarioDTO dto = new FuncionarioDTO();
//                   EmpresaDTO dtoEmp = new EmpresaDTO();
//                   PessoaDTO dtoPes = new PessoaDTO();
//                   PessoaDTO dtoPesEmp = new PessoaDTO();
//                   dto.setNome(fr.getNome());
//                   dto.setSobrenome(fr.getSobrenome());
//                   dto.setCpf(fr.getCpf());
//                   dto.setTelefone(fr.getTelefone());
////                   dto.setLogin(fr.getLogin());
////                   dto.setSenha(fr.getSenha());
//                   dtoEmp.setRazaoSocial(fr.getEmpresa().getRazaoSocial());
//                   dtoPesEmp.setNome(fr.getEmpresa().getGerente().getNome());
//                   dtoPesEmp.setSobrenome(fr.getEmpresa().getGerente().getSobrenome());
//                   dtoPesEmp.setCpf(fr.getEmpresa().getGerente().getCpf());
//                   dtoPesEmp.setTelefone(fr.getEmpresa().getGerente().getTelefone());
//                   dtoEmp.setGerente(dtoPesEmp);
//
//                   dto.setEmpresaFuncionario(dtoEmp);
//                   if (fr.getSupervisor() == null) {
//                       return dto;
//                   } else {
//                       dtoPes.setNome(fr.getSupervisor().getNome());
//                       dtoPes.setSobrenome(fr.getSupervisor().getSobrenome());
//                       dtoPes.setCpf(fr.getSupervisor().getCpf());
//                       dtoPes.setTelefone(fr.getSupervisor().getTelefone());
//                       dto.setSupervisorFuncionario(dtoPes);
//                       return dto;
//                   }
//       }).collect(Collectors.toList());
//  }
//
//    public void saveFuncionario( FuncionarioPayLoadDTO input) {
////        FuncionarioEntity newEntity = (FuncionarioEntity) pessoaRepository.findById(input.getId()).get();
////
////        EmpresaEntity newEmpresa = new EmpresaEntity();
////        newEmpresa.setIdEmpresa(input.getEmpresaFuncionario().getIdEmpresa());
////        newEntity.setEmpresa(newEmpresa);
////
//////        FuncionarioEntity funcionarioS = new FuncionarioEntity();
//////       funcionarioS.setSupervisor(null);
//////       funcionarioS.setIdPessoa(input.getSupervisorFuncionario().getFuncionarioPessoa().getIdPessoa());
//////
//////
//////        newEntity.setSupervisor(funcionarioS);
////
//////        newEntity.setIdPessoa(input.getFuncionarioPessoa().getIdPessoa());
//////        newEntity.setNome(input.getFuncionarioPessoa().getNome());
//////        newEntity.setSobrenome(input.getFuncionarioPessoa().getSobrenome());
//////        newEntity.setCpf(input.getFuncionarioPessoa().getCpf());
//////        newEntity.setTelefone(input.getFuncionarioPessoa().getTelefone());
//////        newEntity.setDesabilitado(input.getFuncionarioPessoa().getDesabilitado());
//////        newEntity.setLogin(input.getFuncionarioPessoa().getLogin());
//////        newEntity.setSenha(input.getFuncionarioPessoa().getSenha());
//////
//////        EmpresaEntity newEmpresa = new EmpresaEntity();
//////        newEmpresa.setIdEmpresa(input.getEmpresaFuncionario().getIdEmpresa());
//////
//////       FuncionarioEntity funcionarioS = new FuncionarioEntity();
//////       funcionarioS.setSupervisor(null);
//////       funcionarioS.setIdPessoa(input.getSupervisorFuncionario().getFuncionarioPessoa().getIdPessoa());
//////
//////
//////        newEntity.setSupervisor(funcionarioS);
//////        newEntity.setEmpresa(newEmpresa);
////
////
////        funcionarioRepository.save(newEntity);
// }
////

}
