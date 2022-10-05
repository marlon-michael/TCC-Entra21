package com.entra21.Transportadora.model.dto.Entrega;

import com.entra21.Transportadora.model.dto.EntregaTrecho.EntregaTrechoDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import lombok.Data;

@Data
public class EntregaDTO {
    private Long idEntrega;
    private String tipoEntrega;
    private PessoaDTO NomeEntregador;
    private EntregaTrechoDTO EntregaTrecho;
}
