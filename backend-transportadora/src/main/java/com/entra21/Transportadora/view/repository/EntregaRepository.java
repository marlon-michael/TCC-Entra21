package com.entra21.Transportadora.view.repository;

import com.entra21.Transportadora.model.entity.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntregaRepository extends JpaRepository<EntregaEntity, Long> {
    public Optional<List<EntregaEntity>> findAllByEntregador_Cpf(String cpf);
    public List<EntregaEntity> findAllByEntregador_Empresa_Cnpj(String cnpj);
}

