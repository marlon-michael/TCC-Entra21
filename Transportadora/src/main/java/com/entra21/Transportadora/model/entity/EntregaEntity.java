package com.entra21.Transportadora.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "entrega")
public class EntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEntrega;

    @Column(name = "tipo_entrega")
    private String tipoEntrega;

    @ManyToOne
    @JoinColumn(name="id_entregador", referencedColumnName = "id_pessoa")
    private FuncionarioEntity entregador;

    @ManyToMany
    @JoinTable(
            name = "entrega_item",
            joinColumns = @JoinColumn(name = "id_entrega"),
            inverseJoinColumns = @JoinColumn(name = "id_item")
    )
    private List<ItemEntity> itens;

    @OneToMany
    @JoinColumn(name = "id_entrega", referencedColumnName = "id")
    private List<EntregaTrechoEntity> entregaTrecho;

}