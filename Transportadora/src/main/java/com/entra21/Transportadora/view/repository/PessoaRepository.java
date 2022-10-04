package com.entra21.Transportadora.view.repository;

import com.entra21.Transportadora.model.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
    public Optional<PessoaEntity> findByLogin(String login);
    public Optional<PessoaEntity> findByCpf(String cpf);
}
