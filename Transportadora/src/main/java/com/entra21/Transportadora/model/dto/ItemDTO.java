package com.entra21.Transportadora.model.dto;

import com.entra21.Transportadora.model.entity.PessoaEntity;
import lombok.Data;

@Data
public class ItemDTO {
    private Long idItem;
    private String localizador;
    private String status;
    private String localEntrega;
    private String nomeRecebedor;
    private Long pessoaItem;
}
