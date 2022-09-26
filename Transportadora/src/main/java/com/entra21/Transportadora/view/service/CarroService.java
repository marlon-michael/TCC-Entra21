package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.*;
import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.model.entity.PessoaEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
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
        CarroEntity newCarro = new CarroEntity();
        newCarro.setTipoCarro(input.getTipoCarro());
        newCarro.setPlaca(input.getPlaca());
        EmpresaEntity newEmpresa = new EmpresaEntity();
        newEmpresa.setIdEmpresa(input.getEmpresaCarro().getIdEmpresa());
        newCarro.setEmpresa(newEmpresa);
        carroRepository.save(newCarro);
    }

    public void deleteCarros(Long id) {
        carroRepository.deleteById(id);
    }

    public List<CarroDTO> getAllCarros() {

      return   carroRepository.findAll().stream().map(cr -> {

            CarroDTO dtocarro = new CarroDTO();
            dtocarro.setTipoCarro(cr.getTipoCarro());
            dtocarro.setPlaca(cr.getPlaca());

            EmpresaAddDTO cr1 = new EmpresaAddDTO();
            cr1.setIdEmpresa(cr.getEmpresa().getIdEmpresa());
            cr1.setRazaoSocial(cr.getEmpresa().getRazaoSocial());

            PessoaPayLoadDTO cr2 = new PessoaPayLoadDTO();
            cr2.setNome(cr.getEmpresa().getGerente().getNome());
            cr2.setCpf(cr.getEmpresa().getGerente().getCpf());
            cr2.setTelefone(cr.getEmpresa().getGerente().getTelefone());
            cr2.setSobrenome(cr.getEmpresa().getGerente().getSobrenome());

            cr1.setGerente(cr2);
            dtocarro.setEmpresaCarro(cr1);

          return dtocarro;
        }).collect(Collectors.toList());

    }

    public CarroDTO updateCarro(Long idcarronv, CarroDTO carroDTO) {
        CarroEntity e = carroRepository.findById(idcarronv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrada!"));

        e.setTipoCarro(carroDTO.getTipoCarro());
        e.setPlaca(carroDTO.getPlaca());
        EmpresaEntity ent = new EmpresaEntity();
        ent.setIdEmpresa(carroDTO.getEmpresaCarro().getIdEmpresa());
        e.setEmpresa(ent);
        e = carroRepository.save(e);
        return carroDTO;
    }
}
