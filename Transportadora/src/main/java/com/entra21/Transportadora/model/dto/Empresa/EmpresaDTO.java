package com.entra21.Transportadora.model.dto.Empresa;

import com.entra21.Transportadora.model.dto.Carro.CarroAddDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import lombok.Data;

import java.util.List;

@Data
public class EmpresaDTO {
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private PessoaDTO gerente;
    private List<CarroDTO> carros;
    private List<FuncionarioDTO> funcionarios;
}
