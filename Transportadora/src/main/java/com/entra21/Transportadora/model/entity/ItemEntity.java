package com.entra21.Transportadora.model.entity;
import lombok.Data;
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
        private  String Localizador;

        @Column(name = "status")
        private String Status;

        @Column(name = "local_entrega")
        private String localEntrega;

        @Column(name = "nome_recebedor")
        private String nomeRecebedor;
}
