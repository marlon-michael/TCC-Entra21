package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioDTO {
    private Long idFuncionario;
    private Long supervisorFuncionario;
    private EmpresaDTO empresaFuncionario;
}
