package com.entra21.Transportadora.model.entity;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "empresa")
public class EmpresaEntity{

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(name = "razao_social")
    private  String razSocial;

    @Column(name = "id_gerente")
    private Integer idGerente;

    @OneToMany
    private Set<CarroEntity> carros;

}