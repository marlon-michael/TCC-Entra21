package com.entra21.Transportadora.model.dto;

import lombok.Data;

@Data
public class PessoaDTO {
    private Long idPessoa;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private String login;
    private  String senha;
}
