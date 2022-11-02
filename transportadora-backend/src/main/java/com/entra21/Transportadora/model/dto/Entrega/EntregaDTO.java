package com.entra21.Transportadora.model.dto.Entrega;
import com.entra21.Transportadora.model.dto.EntregaTrecho.EntregaTrechoDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.Item.ItemDTO;
import lombok.Data;

import java.util.List;

@Data
public class EntregaDTO {
    private Long idEntrega;
    private String tipoEntrega;
    private FuncionarioDTO entregador;
    private List<EntregaTrechoDTO> entregaTrecho;
    private List<ItemDTO> itens;
}
