package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.CarroDTO;
import com.entra21.Transportadora.model.dto.GetAllEmpresasDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        newEmpresa.setIdEmpresa(input.getIdEmpresa());
        newCarro.setEmpresa(newEmpresa);
        carroRepository.save(newCarro);
    }

    public void deleteCarros(Long idEmpresa) {
        carroRepository.deleteById(idEmpresa);
    }

    public List<CarroDTO> getAllCarros() {
      return   carroRepository.findAll().stream().map(cr -> {
            CarroDTO dtocarro = new CarroDTO();
            dtocarro.setTipoCarro(cr.getTipoCarro());
            dtocarro.setPlaca(cr.getPlaca());
            GetAllEmpresasDTO cr1 = new GetAllEmpresasDTO();
            cr1.setRazaoSocial(cr.getEmpresa().getRazaoSocial());

            PessoaDTO cr2 = new PessoaDTO();
            cr2.setNome(cr.getEmpresa().getGerente().getNome());
            cr2.setCpf(cr.getEmpresa().getGerente().getCpf());
            cr2.setTelefone(cr.getEmpresa().getGerente().getTelefone());
            cr2.setSobrenome(cr.getEmpresa().getGerente().getSobrenome());
            cr1.setNomeGerente(cr2);
            dtocarro.setEmpresaCarro(cr1);
            return dtocarro;
        }).collect(Collectors.toList());
    }

    public CarroDTO updateCarro(Long idcarronv, CarroDTO carroDTO) {
        CarroEntity e = carroRepository.findById(idcarronv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrada!"));
//        e.setIdCarro(idcarronv);
        e.setTipoCarro(carroDTO.getTipoCarro());
        e.setPlaca(carroDTO.getPlaca());
        EmpresaEntity ent = new EmpresaEntity();
        ent.setIdEmpresa(carroDTO.getEmpresaCarro().getIdEmpresa());
        e.setEmpresa(ent);
        e = carroRepository.save(e);
        return carroDTO;
    }
}