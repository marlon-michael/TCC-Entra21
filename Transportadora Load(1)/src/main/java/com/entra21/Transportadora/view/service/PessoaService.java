package com.entra21.Transportadora.view.service;


import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PessoaService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PessoaEntity user = pessoaRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public PessoaEntity buscarUsuarioLogado() {
        try {
            return (PessoaEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
