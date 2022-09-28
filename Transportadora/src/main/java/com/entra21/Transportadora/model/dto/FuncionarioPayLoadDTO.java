package com.entra21.Transportadora.model.dto;

import lombok.Data;

@Data
public class FuncionarioPayLoadDTO {
    private Long idFuncionario;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private PessoaDTO supervisorFuncionario;
    private GetAllEmpresasDTO empresaFuncionario;
}
