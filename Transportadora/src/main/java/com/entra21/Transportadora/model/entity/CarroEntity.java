package com.entra21.Transportadora.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "carro")
public class CarroEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarro;

    @Column(name = "tipo")
    private String tipoCarro;

    @Column(name = "placa")
    private String placa;

    @ManyToOne
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    private EmpresaEntity empresa;

    @OneToMany
    @JoinColumn(name = "id_carro", referencedColumnName = "id")
    @JsonIgnore
    private List<EntregaTrechoEntity> entregaTrechos;

}
