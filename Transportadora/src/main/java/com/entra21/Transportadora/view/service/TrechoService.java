package com.entra21.Transportadora.view.service;


import com.entra21.Transportadora.model.dto.TrechoDTO;
import com.entra21.Transportadora.view.repository.TrechoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrechoService {
    @Autowired
    private TrechoRepository trechoRepository;

}
