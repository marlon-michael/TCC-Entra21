package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.PessoaEntity;

import com.entra21.Transportadora.view.repository.EmpresaRepository;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaDTO {
    private Long id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String cpf;
    private String login;
    private String senha;
    private Boolean desabilitado;
    private String role;
    private EmpresaRepository empresaRepositorypessoa;

    public PessoaDTO(PessoaEntity e) {
        this.idPessoa = e.getIdPessoa();
        this.nome = e.getNome();
        this.sobrenome = e.getSobrenome();
        this.telefone = e.getTelefone();
        this.cpf = e.getCpf();
        this.login = e.getLogin();
        this.senha = e.getSenha();
        this.desabilitado = e.getDesabilitado();
        this.role = "PESSOA";
    }

}
