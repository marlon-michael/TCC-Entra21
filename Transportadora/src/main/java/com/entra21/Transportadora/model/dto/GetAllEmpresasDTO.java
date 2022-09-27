package com.entra21.Transportadora.model.dto;

import lombok.Data;

@Data
public class GetAllEmpresasDTO {
    private Long idEmpresa;
    private String razaoSocial;
    private PessoaDTO nomeGerente;
}