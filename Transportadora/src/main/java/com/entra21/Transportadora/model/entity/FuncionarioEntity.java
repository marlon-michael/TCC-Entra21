package com.entra21.Transportadora.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class FuncionarioEntity extends PessoaEntity{

    @Column(name = "id_supervisor")
    private FuncionarioEntity supervisor;

    @Column(name = "id_empresa")
    private EmpresaEntity empresa;

    @OneToMany
    @JoinColumn(name = "id_entregador", referencedColumnName = "id")
    @JsonIgnore
    private List<EntregaEntity> entrega;

}