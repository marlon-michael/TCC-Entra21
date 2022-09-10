package com.entra21.Transportadora.model.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private String localizador;
    private String status;
    private String localEntrega;
    private String nomeDestinatário;
}
