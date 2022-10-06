package com.entra21.Transportadora.view.repository;
import com.entra21.Transportadora.model.entity.FuncionarioEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository  extends JpaRepository<FuncionarioEntity, Long> {
    public Optional<FuncionarioEntity> findByCpf(String cpf);
}
