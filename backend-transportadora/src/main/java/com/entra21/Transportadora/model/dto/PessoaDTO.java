package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.PessoaEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaDTO {
    private Long idPessoa;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private String login;
    private String senha;
    private Boolean desabilitado;
    private String cargo;
    // private EmpresaRepository empresaRepositorypessoa;

    public PessoaDTO(PessoaEntity pessoa) {
        this.idPessoa = pessoa.getIdPessoa();
        this.nome = pessoa.getNome();
        this.sobrenome = pessoa.getSobrenome();
        this.telefone = pessoa.getTelefone();
        this.cpf = pessoa.getCpf();
        this.login = pessoa.getLogin();
        this.senha = pessoa.getSenha();
        this.desabilitado = pessoa.getDesabilitado();
        this.cargo = "PESSOA";
    }

    public String getUsername() {
        return login;
    }

    public String getPassword() {
        return senha;
    }

}
