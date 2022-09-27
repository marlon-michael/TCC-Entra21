package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.EmpresaDTO;
import com.entra21.Transportadora.model.dto.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.dto.TrechoDTO;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.repository.TrechoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

   @Autowired
   private FuncionarioRepository funcionarioRepository;

//   public List<FuncionarioDTO> getAllFuncionario() {
//       return null;
//   }}
//       return funcionarioRepository.findAll().stream().map(fr -> {
//           FuncionarioDTO dto = new FuncionarioDTO();
//           PessoaDTO
//           dto.setIdFuncionario(fr.getIdPessoa());
//           dto.setEmpresaFuncionario(fr.getEmpresa().getIdEmpresa());
//           if (fr.getSupervisor().getIdPessoa() == null){
//               return dto;
//           }else{
//               dto.setSupervisorFuncionario(fr.getSupervisor().getIdPessoa());
//               return dto;
//           }
//       }).collect(Collectors.toList());
//   }
   public List<FuncionarioDTO> getAllFuncionario() {
       return funcionarioRepository.findAll().stream().map(fr -> {
            FuncionarioDTO dto = new FuncionarioDTO();
            dto.setIdFuncionario(fr.getIdPessoa());

            EmpresaDTO empresaDTO = new EmpresaDTO();
            PessoaDTO pessoaDTO = new PessoaDTO();
            empresaDTO.setRazaoSocial(fr.getEmpresa().getRazaoSocial());
            empresaDTO.setGerente(dto.getEmpresaFuncionario().getGerente());
            dto.setEmpresaFuncionario(empresaDTO);

            return dto;
       }).collect(Collectors.toList());
   }
}

