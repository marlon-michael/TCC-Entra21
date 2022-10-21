package com.entra21.Transportadora.model.dto.Entrega;
import com.entra21.Transportadora.model.dto.EntregaTrecho.EntregaTrechoUpDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioUpDTO;
import com.entra21.Transportadora.model.dto.Item.ItemUpDTO;
import lombok.Data;

import java.util.List;


@Data
public class EntregaUpDTO {
    private Long idEntrega;
    private String tipoEntrega;
    private FuncionarioUpDTO entregador;
    private List<EntregaTrechoUpDTO> entregaTrecho;
    private List<ItemUpDTO> itens;
}

