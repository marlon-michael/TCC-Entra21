package com.entra21.Transportadora.model.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "funcionario")
public class FuncionarioEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;

    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Column(name = "id_supervisor")
    private Integer idSupervisor;

    @Column(name = "id_empresa")
    private Integer idEmpresa;

}