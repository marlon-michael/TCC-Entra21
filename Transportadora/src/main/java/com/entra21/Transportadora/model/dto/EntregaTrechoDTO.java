package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EntregaEntity;
import com.entra21.Transportadora.model.entity.TrechoEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntregaTrechoDTO {
    private Long idEntregaTrecho;
    private  Integer Completo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private TrechoEntity trecho;
    private CarroEntity carro;
    private EntregaEntity entrega;
}
