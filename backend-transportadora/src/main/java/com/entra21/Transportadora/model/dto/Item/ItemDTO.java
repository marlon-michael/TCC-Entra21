package com.entra21.Transportadora.model.dto.Item;

import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
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
