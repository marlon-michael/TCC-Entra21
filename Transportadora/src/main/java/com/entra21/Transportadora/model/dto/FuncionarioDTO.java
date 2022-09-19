package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.PessoaEntity;
import lombok.Data;

@Data
public class FuncionarioDTO {
    private Long idFuncionario;
    private PessoaEntity supervisorFuncionario;
    private EmpresaEntity empresaFuncionario;
}