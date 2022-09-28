package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioDTO {
//    private Long idFuncionario;
//    private PessoaDTO supervisorFuncionario;
//    private GetAllEmpresasDTO empresaFuncionario;
//    private Long idSupervisor;
    private Long idPessoa;
    private Long idEmpresa;
    private Long idSupervisor;
}
