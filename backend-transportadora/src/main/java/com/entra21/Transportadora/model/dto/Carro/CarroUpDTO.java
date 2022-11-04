package com.entra21.Transportadora.model.dto.Carro;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaUpDTO;
import lombok.Data;

@Data
public class CarroUpDTO {
    private Long idCarro;
    private String tipoCarro;
    private String placa;
    private EmpresaUpDTO empresaCarro;
}
