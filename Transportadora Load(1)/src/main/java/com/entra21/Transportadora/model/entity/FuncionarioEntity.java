package com.entra21.Transportadora.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "funcionario")
public class FuncionarioEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

//
//    @Column(name = "id_pessoa")
//    private Long idPessoa;

    @Column(name = "id_supervisor")
    private Long idSupervisor;

    @Column(name = "id_empresa")
    private Long idEmpresa;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    private PessoaEntity pessoa;

    @OneToMany
    @JoinColumn(name = "id_entregador", referencedColumnName = "id")
    @JsonIgnore
    private List<EntregaEntity> entrega;


}