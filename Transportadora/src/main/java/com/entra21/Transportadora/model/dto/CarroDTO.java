package com.entra21.Transportadora.model.dto;

import lombok.Data;

@Data
public class CarroDTO {
    private Long idCarro;
    private String tipoCarro;
    private String placa;
    private EmpresaEntity empresaCarro;
}
