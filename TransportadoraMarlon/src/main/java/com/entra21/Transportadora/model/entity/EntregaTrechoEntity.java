package com.entra21.Transportadora.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "entrega_trecho")
public class EntregaTrechoEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long idEntregaTrecho;

        @Column(name = "completo")
        private  Integer Completo;

        @Column(name = "data_inicio")
        private LocalDateTime dataInicio;

        @Column(name = "data_fim")
        private LocalDateTime dataFim;

<<<<<<<< HEAD:Transportadora.maria/src/main/java/com/entra21/Transportadora/model/entity/EntregaTrechoEntity.java
//        @OneToMany
//        @JoinColumn(name = "id", referencedColumnName = "id_trecho")
//        private List<TrechoEntity> trechos;
========
        @OneToOne
        @JoinColumn(name = "id_trecho", referencedColumnName = "id")
        private TrechoEntity trecho;

        @ManyToOne
        @JoinColumn(name = "id_carro", referencedColumnName = "id")
        private CarroEntity carro;
>>>>>>>> dbbd225be887d4e020793762a32703eb9b8a68bb:TransportadoraMarlon/src/main/java/com/entra21/Transportadora/model/entity/EntregaTrechoEntity.java

        @ManyToOne
        @JoinColumn(name = "id_entrega", referencedColumnName = "id")
        @JsonIgnore
        private EntregaEntity entrega;

}
