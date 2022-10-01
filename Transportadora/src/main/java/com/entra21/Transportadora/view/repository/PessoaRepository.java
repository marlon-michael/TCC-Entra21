package com.entra21.Transportadora.view.repository;

import com.entra21.Transportadora.model.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Long> {
    public PessoaEntity findByLogin(String login);
    public PessoaEntity findByCpf(String cpf);
}
