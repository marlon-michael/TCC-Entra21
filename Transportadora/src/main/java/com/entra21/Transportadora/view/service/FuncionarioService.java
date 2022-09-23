package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.FuncionarioDTO;
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

   public List<FuncionarioDTO> getAllFuncionario() {
       return funcionarioRepository.findAll().stream().map(fr -> {
           FuncionarioDTO dto = new FuncionarioDTO();
           dto.setIdFuncionario(fr.getIdPessoa());
//           dto.setEmpresaFuncionario(fr.getEmpresa().getIdEmpresa());
//           if (fr.getSupervisor().getIdPessoa() == null){
//               return dto;
//           }else{
//               dto.setSupervisorFuncionario(fr.getSupervisor().getIdPessoa());
//               return dto;
//           }
           return dto;
       }).collect(Collectors.toList());
   }
}
