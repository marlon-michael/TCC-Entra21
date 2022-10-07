package com.entra21.Transportadora.model.dto.Empresa;

import com.entra21.Transportadora.model.dto.Carro.CarroAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import lombok.Data;

import java.util.List;

@Data
public class EmpresaAddDTO {
    private Long id;
    private String razaoSocial;
    private PessoaAddDTO gerente;
    private List<CarroAddDTO> carros;
    private List<FuncionarioAddDTO> funcionarios;
}
