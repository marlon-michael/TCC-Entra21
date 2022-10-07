package com.entra21.Transportadora.model.dto.Entrega;
import com.entra21.Transportadora.model.dto.EntregaTrecho.EntregaTrechoDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioAddDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.Item.ItemDTO;
import com.entra21.Transportadora.model.dto.Item.ItemUpDTO;
import lombok.Data;

import java.util.HashSet;
import java.util.List;


@Data
public class EntregaDTO {
    private Long idEntrega;
    private String tipoEntrega;
    private FuncionarioDTO entregador;
    private List<EntregaTrechoDTO> entregaTrecho;
    private List<ItemDTO> itens;
}
