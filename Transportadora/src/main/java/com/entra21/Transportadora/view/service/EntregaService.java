package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.CarroDTO;
import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import com.entra21.Transportadora.view.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;

    public void saveCarros(CarroDTO input) {
        CarroEntity newEntity = new CarroEntity();
        newEntity.setIdCarro(input.getIdCarro());
        newEntity.setTipoCarro(input.getTipoCarro());
        newEntity.setPlaca(input.getPlaca());
//        newEntity.setEmpresa(input.getEmpresaCarro());
        carroRepository.save(newEntity);
    }
}
