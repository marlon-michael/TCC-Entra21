package com.entra21.Transportadora.model.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "carro")
public class CarroEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarro;

    @Column(name = "tipo")
    private  String tipoCarro;

    @Column(name = "placa")
    private String placa;

    @Column(name = "id_empresa")
    private Long idEmpresa;

}
