package com.entra21.Transportadora.view.repository;



import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<CarroEntity, Long> {
    public Optional<List<CarroEntity>> findAllByEmpresa(EmpresaEntity empresa);
    public Optional<CarroEntity> findByPlaca(String placa);
}

