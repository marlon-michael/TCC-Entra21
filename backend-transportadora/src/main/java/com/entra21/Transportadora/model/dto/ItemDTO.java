package com.entra21.Transportadora.model.dto;

import lombok.Data;

@Data
public class ItemDTO {
//    private Long idItem;
    private String localizador;
    private String status;
    private String localEntrega;
    private String nomeRecebedor;
    private PessoaDTO pessoaItem;
}
