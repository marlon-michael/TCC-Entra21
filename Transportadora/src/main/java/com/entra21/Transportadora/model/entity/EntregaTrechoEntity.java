package com.entra21.Transportadora.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

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
        private Integer Completo;

        @Column(name = "data_inicio")
        @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
        private LocalDateTime dataInicio;

        @Column(name = "data_fim")
        @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
        private LocalDateTime dataFim;

        @OneToOne
        @JoinColumn(name = "id_trecho", referencedColumnName = "id")
        private TrechoEntity trecho;

        @ManyToOne
        @JoinColumn(name = "id_carro", referencedColumnName = "id")
        private CarroEntity carro;

        @ManyToOne
        @JoinColumn(name = "id_entrega", referencedColumnName = "id")
        @JsonIgnore
        private EntregaEntity entrega;

}
