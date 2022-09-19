package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.CarroDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public void saveCarros(CarroDTO input) {
        CarroEntity newEntity = new CarroEntity();
        newEntity.setTipoCarro(input.getTipoCarro());
        newEntity.setPlaca(input.getPlaca());
        newEntity.setIdCarro(input.getIdCarro());
//        newEntity.setEmpresa(input.getEmpresaCarro());
        carroRepository.save(newEntity);
    }

    public void deleteCarros(Long id) {
        carroRepository.deleteById(id);
    }

    public List<CarroDTO> getAllCarros() {
        return carroRepository.findAll().stream().map(cr -> {
            CarroDTO dtocarro = new CarroDTO();
            dtocarro.setIdCarro(cr.getIdCarro());
            dtocarro.setTipoCarro(cr.getTipoCarro());
            dtocarro.setPlaca(cr.getPlaca());
//            dtocarro.setEmpresaCarro(cr.getEmpresa());
            return dtocarro;
        }).collect(Collectors.toList());
    }
// private String tipoCarro;
//    private String placa;
//    private EmpresaEntity empresaCarro;
    public CarroDTO updateCarro(Long idcarronv,String novoTipoCarro, String novoPlaca) {
        CarroEntity e = carroRepository.findById(idcarronv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrada!"));
        e.setIdCarro(idcarronv);
        e.setTipoCarro(novoTipoCarro);
        e.setPlaca(novoPlaca);
//        e.setEmpresa(empresaEntity);
        e = carroRepository.save(e);
        CarroDTO dto = new CarroDTO();
        dto.setIdCarro(e.getIdCarro());
        dto.setTipoCarro(e.getTipoCarro());
        dto.setPlaca(e.getPlaca());
//        dto.setEmpresaCarro(e.getEmpresa());
        return dto;
    }
}
