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

    @Column(name = "id_entregador")
    private Integer idEntregador;

    @Column(name = "tipo_entrega")
    private Integer tipoEntrega;

//    @ArrombaHotmail

}