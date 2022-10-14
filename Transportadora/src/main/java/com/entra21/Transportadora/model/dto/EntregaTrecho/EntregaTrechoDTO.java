package com.entra21.Transportadora.model.dto.EntregaTrecho;
import com.entra21.Transportadora.model.dto.Carro.CarroDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaAddDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaDTO;
import com.entra21.Transportadora.model.dto.Trecho.TrechoDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EntregaTrechoDTO {

        private Boolean Completo;
        private LocalDateTime dataInicio;
        private LocalDateTime dataFim;
        private TrechoDTO trecho;
        private CarroDTO carro;
        private EntregaDTO entrega;
    }


