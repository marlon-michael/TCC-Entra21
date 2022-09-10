package com.entra21.Transportadora.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "entrega")
public class EntregaEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;

    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Column(name = "id_supervisor")
    private Integer idSupervisor;

    @Column(name = "id_empresa")
    private Integer idEmpresa;

}