package com.entra21.Transportadora.model.dto.Empresa;

import com.entra21.Transportadora.model.dto.Pessoa.PessoaAddDTO;
import lombok.Data;



@Data
public class EmpresaAddDTO {
    private Long id;
    private String cnpj;
    private String razaoSocial;
    private PessoaAddDTO gerente;

}
