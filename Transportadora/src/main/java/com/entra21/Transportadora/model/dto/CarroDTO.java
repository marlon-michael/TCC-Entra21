package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.EmpresaEntity;
import lombok.Data;

import java.util.List;

@Data
public class CarroDTO {
    private String tipoCarro;
    private String placa;
    private EmpresaDTO empresaCarro;
}
