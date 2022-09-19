package com.entra21.Transportadora.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
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
    private  String razaoSocial;

    @Column(name = "id_gerente")
    private Integer idGerente;

    @OneToMany
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    private List<CarroEntity> carroEntities;
}