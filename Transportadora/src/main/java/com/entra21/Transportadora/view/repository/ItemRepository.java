package com.entra21.Transportadora.view.repository;
import com.entra21.Transportadora.model.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    public ItemEntity findByLocalizador(String localizador);
}
