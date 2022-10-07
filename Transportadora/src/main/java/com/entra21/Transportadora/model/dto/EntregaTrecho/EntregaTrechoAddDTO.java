package com.entra21.Transportadora.model.dto.EntregaTrecho;

import com.entra21.Transportadora.model.dto.Carro.CarroAddDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaAddDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaDTO;
import com.entra21.Transportadora.model.dto.Trecho.TrechoAddDTO;
import com.entra21.Transportadora.model.dto.Trecho.TrechoDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntregaTrechoAddDTO {
    private Long idEntregaTrecho;
    private Integer Completo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private TrechoAddDTO trecho;
    private CarroAddDTO carro;
    private EntregaAddDTO entrega;
}
