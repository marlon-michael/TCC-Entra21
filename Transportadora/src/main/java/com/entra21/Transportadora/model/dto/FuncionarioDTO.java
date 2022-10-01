package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioDTO extends PessoaDTO{
    private PessoaDTO supervisorFuncionario;
    private EmpresaDTO empresaFuncionario;
}
