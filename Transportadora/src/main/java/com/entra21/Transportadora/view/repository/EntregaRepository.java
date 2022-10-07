package com.entra21.Transportadora.view.repository;

import com.entra21.Transportadora.model.entity.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<EntregaEntity, Long> {
    public List<EntregaEntity> findAllByEntregador_Cpf(String cpf);
}
