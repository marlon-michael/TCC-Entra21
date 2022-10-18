package com.entra21.Transportadora.model.dto.Pessoa;

import lombok.Data;

@Data
public class PessoaLoad {
    private Long idPessoa;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private String login;
    private String senha;
    private Boolean desabilitado;
}
