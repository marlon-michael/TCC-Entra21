package com.entra21.Transportadora.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pessoa")
public class PessoaEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;
}
