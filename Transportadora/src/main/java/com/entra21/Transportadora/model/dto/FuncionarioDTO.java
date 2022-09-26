package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioDTO {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
//    private String login;
//    private  String senha;
    private PessoaDTO supervisorFuncionario;
    private EmpresaDTO empresaFuncionario;
}
