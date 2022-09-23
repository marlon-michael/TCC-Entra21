package com.entra21.Transportadora.view.service;


import com.entra21.Transportadora.model.dto.TrechoDTO;
import com.entra21.Transportadora.model.entity.TrechoEntity;
import com.entra21.Transportadora.view.repository.TrechoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrechoService {
    @Autowired
    private TrechoRepository trechoRepository;


    public List<TrechoDTO> getAllTrecho() {
        return trechoRepository.findAll().stream().map(fr -> {
            TrechoDTO dto = new TrechoDTO();
            dto.setIdTrecho(fr.getIdTrecho());
            dto.setLocalInicio(fr.getLocalInicio());
            dto.setLocalFim(fr.getLocalFim());
            return dto;
        }).collect(Collectors.toList());
    }

    public void saveTrecho(TrechoDTO input) {
        TrechoEntity newEntity = new TrechoEntity();
        newEntity.setIdTrecho(input.getIdTrecho());
        newEntity.setLocalInicio(input.getLocalInicio());
        newEntity.setLocalFim(input.getLocalFim());
        trechoRepository.save(newEntity);
    }

    public void deleteTrecho(Long id) {
        trechoRepository.deleteById(id);
    }

    public TrechoDTO updateTrecho(Long id, TrechoDTO trechoDTO) {
        TrechoEntity e = trechoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquia não encontrada!"));
        e.setLocalInicio(trechoDTO.getLocalInicio());
        e.setLocalFim(trechoDTO.getLocalFim());
//        trechoDTO.setIdTrecho(e.getIdTrecho());
        e = trechoRepository.save(e);
        TrechoDTO dto = new TrechoDTO();
        dto.setIdTrecho(e.getIdTrecho());
        dto.setLocalFim(e.getLocalFim());
        dto.setLocalInicio(e.getLocalInicio());
        return dto;

    }
}

