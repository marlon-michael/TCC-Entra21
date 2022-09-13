package com.entra21.Transportadora.model.entity;
import lombok.Data;
import javax.persistence.*;

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

}
