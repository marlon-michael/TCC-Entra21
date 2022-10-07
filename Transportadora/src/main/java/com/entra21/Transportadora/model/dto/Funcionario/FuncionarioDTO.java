package com.entra21.Transportadora.model.dto.Funcionario;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.EntregaEntity;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioDTO extends PessoaDTO {
    private FuncionarioDTO supervisor;
    private List<EntregaDTO> entrega;
    private EmpresaDTO empresa;
}
