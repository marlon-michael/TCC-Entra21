package com.entra21.Transportadora.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "trecho")
public class TrechoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idTrecho;

    @Column(name = "local_inicio")
    private String LocalInicio;

    @Column(name = "local_fim")
    private String localFim;
//
//<<<<<<<< HEAD:Transportadora.maria/src/main/java/com/entra21/Transportadora/model/entity/TrechoEntity.java
//    @ManyToOne
//    @JoinColumn(name = "id_trecho", referencedColumnName = "id")
//    private EntregaTrechoEntity entregaTrecho;

}
