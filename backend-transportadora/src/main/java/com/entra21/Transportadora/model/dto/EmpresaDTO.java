package com.entra21.Transportadora.model.dto;

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
