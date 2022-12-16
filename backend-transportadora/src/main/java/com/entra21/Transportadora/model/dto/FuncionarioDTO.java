package com.entra21.Transportadora.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class FuncionarioDTO extends PessoaDTO {
    private FuncionarioDTO supervisor;
    private List<EntregaDTO> entregas;
    private EmpresaDTO empresa;
}
