package com.entra21.Transportadora.model.dto.Item;

import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import lombok.Data;

@Data
public class ItemUpDTO {
    private Long idItem;
    private String localizador;
    private String status;
    private String localEntrega;
    private String nomeRecebedor;
    private PessoaAddDTO pessoaItem;
}
