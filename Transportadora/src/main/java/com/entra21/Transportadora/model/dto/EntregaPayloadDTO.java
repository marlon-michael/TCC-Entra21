package com.entra21.Transportadora.model.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.List;

@Data
public class EntregaPayloadDTO {
    private String tipoEntrega;
    private FuncionarioPayLoadDTO entregador;
    private List<EntregaTrechoPayloadDTO> entregaTrecho;
    private HashSet<ItemDTO> itens;
}
