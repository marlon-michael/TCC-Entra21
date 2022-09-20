package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.PessoaEntity;
import lombok.Data;

import java.util.List;

@Data
public class EmpresaDTO {
    private Long idEmpresa;
    private String razaoSocial;
    private String gerente;
}