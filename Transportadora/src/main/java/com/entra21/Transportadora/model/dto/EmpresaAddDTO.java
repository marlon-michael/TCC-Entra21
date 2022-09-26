package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.PessoaEntity;
import lombok.Data;

import java.util.List;

@Data
public class EmpresaAddDTO {
    private Long idEmpresa;
    private String razaoSocial;
    private Long idGerente;
}
