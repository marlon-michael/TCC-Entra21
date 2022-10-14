package com.entra21.Transportadora.model.dto.Carro;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import lombok.Data;

@Data
public class CarroDTO {
    private String tipoCarro;
    private String placa;
    private EmpresaDTO empresaCarro;
}
