package com.entra21.Transportadora.model.dto.Funcionario;

import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class FuncionarioDTO extends PessoaDTO {
    private FuncionarioDTO supervisor;
    private List<EntregaDTO> entrega;
    private EmpresaDTO empresa;

//    public FuncionarioDTO(FuncionarioEntity e) {
//        this.setEntrega(e.setEntrega(this.entrega..getTipoEntrega()));
//        this.nome = e.getNome();
//        this.sobrenome = e.getSobrenome();
//        this.telefone = e.getTelefone();
//        this.cpf = e.getCpf();
//        this.login = e.getLogin();
//        this.senha = e.getSenha();
//        this.desabilitado = e.getDesabilitado();
//        this.role = "GERENTE";
//    }
}
