package com.entra21.Transportadora.model.dto;

import lombok.Data;

@Data
public class EmpresaAddDTO {
    private Long idEmpresa;
    private String razaoSocial;
    private PessoaPayLoadDTO gerente;
}
