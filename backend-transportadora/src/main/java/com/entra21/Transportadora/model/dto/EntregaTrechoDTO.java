package com.entra21.Transportadora.model.dto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntregaTrechoDTO {
    private Long idEntregaTrecho;
    private Boolean completo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private TrechoDTO trecho;
    private CarroDTO carro;
    private EntregaDTO entrega;
}


