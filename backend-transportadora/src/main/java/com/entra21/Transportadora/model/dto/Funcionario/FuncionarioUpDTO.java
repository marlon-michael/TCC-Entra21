package com.entra21.Transportadora.model.dto.Funcionario;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaUpDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaUpDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaUpDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class FuncionarioUpDTO extends PessoaUpDTO {
    private FuncionarioUpDTO supervisor;
    private List<EntregaUpDTO> entrega;
    private EmpresaUpDTO empresa;
}
