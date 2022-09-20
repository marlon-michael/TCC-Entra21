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

//    @ManyToOne
//    @JoinColumn(name = "id_supervisor",referencedColumnName = "id_pessoa")
//    private FuncionarioEntity supervisor;

    @ManyToOne
    @JoinColumn(name = "id_empresa",referencedColumnName = "id")
    private EmpresaEntity empresa;

//    @OneToMany
//    @JoinColumn(name = "id_entregador", referencedColumnName = "id_pessoa")
//    @JsonIgnore
//    private List<EntregaEntity> entrega;
@OneToMany(fetch = FetchType.LAZY)
@JoinColumn(name="id_entregador", referencedColumnName = "id_pessoa")
private List<EntregaEntity> entrega;
}