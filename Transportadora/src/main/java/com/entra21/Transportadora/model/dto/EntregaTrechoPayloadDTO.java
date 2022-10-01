package com.entra21.Transportadora.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntregaTrechoPayloadDTO {
    private Long idEntregaTrecho;
    private Integer Completo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private TrechoDTO trecho;
    private CarroDTO carro;
    private EntregaPayloadDTO entrega;
}
