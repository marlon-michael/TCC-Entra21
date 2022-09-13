package com.entra21.Transportadora.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class EntregaTrechoDTO {
    private Boolean completoEntrega;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
