package com.entra21.Transportadora.view.repository;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long>  {
    public Optional<List<EmpresaEntity>> findByCnpj(String cnpj);
}
