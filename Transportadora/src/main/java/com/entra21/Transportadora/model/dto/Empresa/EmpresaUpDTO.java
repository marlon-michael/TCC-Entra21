package com.entra21.Transportadora.model.dto.Empresa;

import com.entra21.Transportadora.model.dto.Carro.CarroAddDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroUpDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioUpDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaUpDTO;
import lombok.Data;

import java.util.List;
@Data
public class EmpresaUpDTO {
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private PessoaUpDTO gerente;
    private List<CarroUpDTO> carros;
    private List<FuncionarioUpDTO> funcionarios;
}
