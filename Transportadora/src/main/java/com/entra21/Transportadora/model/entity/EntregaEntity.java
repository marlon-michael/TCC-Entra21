package com.entra21.Transportadora.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "entrega")
public class EntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEntrega;

    @Column(name = "tipo_entrega")
    private String tipoEntrega;
//
//    @Column(name = "id_entregador")
//    private PessoaEntity entregador1;

    @ManyToOne
    @JoinColumn(name="id_entregador", referencedColumnName = "id_pessoa")
    private FuncionarioEntity entregador;

    @OneToMany
    @JoinColumn(name = "id_entrega", referencedColumnName = "id")
    private List<EntregaTrechoEntity> entregaTrecho;


}