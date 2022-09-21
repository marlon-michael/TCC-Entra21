package com.entra21.Transportadora.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "empresa")
public class EmpresaEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;

    @Column(name = "razao_social")
    private  String razaoSocial;

    @Column(name = "id_gerente")
    private String idGerente;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @JsonIgnore
    private List<CarroEntity> carroEntities;
}