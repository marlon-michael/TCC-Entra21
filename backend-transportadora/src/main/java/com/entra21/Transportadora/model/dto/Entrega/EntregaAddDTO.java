package com.entra21.Transportadora.model.dto.Entrega;

import com.entra21.Transportadora.model.dto.EntregaTrecho.EntregaTrechoDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Item.ItemDTO;
import lombok.Data;

import java.util.List;

@Data
public class EntregaAddDTO {
        private Long idEntrega;
        private String tipoEntrega;
        private FuncionarioAddDTO entregador;
        private List<EntregaTrechoDTO> entregaTrecho;
        private List<ItemDTO> itens;
    }
