package com.entra21.Transportadora.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class ItemEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @Column(name = "localizador")
    private  String localizador;

    @Column(name = "status")
    private String status;

    @Column(name = "local_entrega")
    private String localEntrega;

    @Column(name = "nome_recebedor")
    private String nomeRecebedor;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @JsonIgnore
    private PessoaEntity pessoa;
}
