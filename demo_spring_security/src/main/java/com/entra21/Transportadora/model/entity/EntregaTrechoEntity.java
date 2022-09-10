package com.entra21.Transportadora.model.entity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "entrega_trecho")
public class EntregaTrechoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "id_entrega")
        private Long idEntregaTrecho;

        @Column(name = "id_trecho")
        private Long idTrecho;

        @Column(name = "completo")
        private  Integer Completo;

        @Column(name = "data_inicio")
        private LocalDateTime dataInicio;

        @Column(name = "data_fim")
        private LocalDateTime dataFim;

        @Column(name = "id_carro")
        private Integer idCarro;

}
