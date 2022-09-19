package com.entra21.Transportadora.view.repository;
import com.entra21.Transportadora.model.entity.TrechoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrechoRepository extends JpaRepository<TrechoEntity, Long> {
}
