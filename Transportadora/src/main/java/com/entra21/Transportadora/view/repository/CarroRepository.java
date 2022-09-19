package com.entra21.Transportadora.view.repository;



import com.entra21.Transportadora.model.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<CarroEntity, Long> {
}

