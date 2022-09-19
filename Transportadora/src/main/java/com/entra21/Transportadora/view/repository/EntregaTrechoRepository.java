package com.entra21.Transportadora.view.repository;

import com.entra21.Transportadora.model.entity.EntregaTrechoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaTrechoRepository extends JpaRepository<EntregaTrechoEntity, Long>{

}