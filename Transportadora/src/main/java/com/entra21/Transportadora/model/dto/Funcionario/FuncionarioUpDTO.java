package com.entra21.Transportadora.model.dto.Funcionario;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaUpDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaUpDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaUpDTO;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.EntregaEntity;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioUpDTO extends PessoaUpDTO {
    private FuncionarioUpDTO supervisor;
    private List<EntregaUpDTO> entrega;
    private EmpresaUpDTO empresa;
}
