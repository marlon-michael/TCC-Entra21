package com.entra21.Transportadora.model.dto.Funcionario;

import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.EntregaEntity;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioAddDTO extends PessoaAddDTO {
    private FuncionarioEntity supervisor;
    private List<EntregaEntity> entrega;
    private EmpresaEntity empresa;
}
