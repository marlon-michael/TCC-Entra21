package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.CarroDTO;
import com.entra21.Transportadora.model.dto.EmpresaDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
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
        CarroEntity newEntity = new CarroEntity();
        newEntity.setIdCarro(input.getIdCarro());
        newEntity.setTipoCarro(input.getTipoCarro());
        newEntity.setPlaca(input.getPlaca());
//       newEntity.setEmpresa(input.getEmpresaCarro());
        carroRepository.save(newEntity);
    }

    public void deleteCarros(Long id) {
        carroRepository.deleteById(id);
    }

    public List<CarroDTO> getAllCarros() {

        return   carroRepository.findAll().stream().map(cr -> {

            CarroDTO dtocarro = new CarroDTO();
            dtocarro.setTipoCarro(cr.getTipoCarro());
            dtocarro.setPlaca(cr.getPlaca());
            dtocarro.setIdCarro(cr.getIdCarro());

            EmpresaDTO cr1 = new EmpresaDTO();
            cr1.setRazaoSocial(cr.getEmpresa().getRazaoSocial());


            PessoaDTO cr2 = new PessoaDTO();
            cr2.setNome(cr.getEmpresa().getGerente().getNome());
            cr2.setCpf(cr.getEmpresa().getGerente().getCpf());
            cr2.setTelefone(cr.getEmpresa().getGerente().getTelefone());
            cr2.setSobrenome(cr.getEmpresa().getGerente().getSobrenome());

            cr1.setGerente(cr2);
            dtocarro.setEmpresaCarro(cr1);

            return dtocarro;
        }).collect(Collectors.toList());

    }
    // private String tipoCarro;
//    private String placa;
//    private EmpresaEntity empresaCarro;
    public CarroDTO updateCarro(Long idcarronv, CarroDTO carroDTO) {
        CarroEntity e = carroRepository.findById(idcarronv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrada!"));
//        e.setIdCarro(idcarronv);
        e.setTipoCarro(carroDTO.getTipoCarro());
        e.setPlaca(carroDTO.getPlaca());

//        e.setEmpresa(carroDTO.getEmpresaCarro());
        e = carroRepository.save(e);
        carroDTO.setIdCarro(e.getIdCarro());
//        CarroDTO dto = new CarroDTO();
//        dto.setIdCarro(e.getIdCarro());
//        dto.setTipoCarro(e.getTipoCarro());
//        dto.setPlaca(e.getPlaca());
//        dto.setEmpresaCarro(e.getEmpresa());
        return carroDTO;
    }
}