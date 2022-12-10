package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;

import lombok.Data;

@Data
public class CarroDTO {
    private Long id;
    private String nome;
    private String placa;
    private EmpresaDTO empresa;
}
