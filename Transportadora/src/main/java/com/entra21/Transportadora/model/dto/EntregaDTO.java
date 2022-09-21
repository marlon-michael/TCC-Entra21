package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import lombok.Data;

@Data
public class EntregaDTO {
    private Long idEntrega;
    private String tipoEntrega;
    private FuncionarioEntity entregador;
}
