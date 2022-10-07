package com.entra21.Transportadora.model.dto.EntregaTrecho;

import com.entra21.Transportadora.model.dto.Carro.CarroAddDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroUpDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaAddDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaUpDTO;
import com.entra21.Transportadora.model.dto.Trecho.TrechoAddDTO;
import com.entra21.Transportadora.model.dto.Trecho.TrechoUpDTO;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class EntregaTrechoUpDTO {
    private Long idEntregaTrecho;
    private Integer Completo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private TrechoUpDTO trecho;
    private CarroUpDTO carro;
    private EntregaUpDTO entrega;
}

