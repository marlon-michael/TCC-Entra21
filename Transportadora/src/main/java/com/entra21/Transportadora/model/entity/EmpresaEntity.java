package com.entra21.Transportadora.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "empresa")
public class EmpresaEntity{

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(name = "razao_social")
    private  String razaoSocial;

    @Column(name = "id_gerente")
    private PessoaEntity gerente;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
//    private List<CarroEntity> carroEntities;
}