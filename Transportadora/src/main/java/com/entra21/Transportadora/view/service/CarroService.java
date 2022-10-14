package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.Carro.CarroAddDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroDTO;
import com.entra21.Transportadora.model.dto.Carro.CarroUpDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public CarroDTO getCarroByPlaca(String placa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        CarroDTO carroDTO = new CarroDTO();
        CarroEntity carroEntity = carroRepository.findByPlaca(placa).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro/Placa não encontrado");
        });

        pessoaDTO.setNome(carroEntity.getEmpresa().getGerente().getNome());
        pessoaDTO.setCpf(carroEntity.getEmpresa().getGerente().getCpf());
        pessoaDTO.setTelefone(carroEntity.getEmpresa().getGerente().getTelefone());
        pessoaDTO.setSobrenome(carroEntity.getEmpresa().getGerente().getSobrenome());

        empresaDTO.setGerente(pessoaDTO);
        empresaDTO.setRazaoSocial(carroEntity.getEmpresa().getRazaoSocial());
        empresaDTO.setCnpj(carroEntity.getEmpresa().getCnpj());

        carroDTO.setEmpresaCarro(empresaDTO);
        carroDTO.setTipoCarro(carroEntity.getTipoCarro());
        carroDTO.setPlaca(carroEntity.getPlaca());

        return carroDTO;
    }

    public List<CarroDTO> getAllCarros() {
        return carroRepository.findAll().stream().map(carroEntity -> {
            CarroDTO dtocarro = new CarroDTO();
            EmpresaDTO empresaDTO = new EmpresaDTO();
            PessoaDTO pessoaDTO = new PessoaDTO();

            pessoaDTO.setNome(carroEntity.getEmpresa().getGerente().getNome());
            pessoaDTO.setCpf(carroEntity.getEmpresa().getGerente().getCpf());
            pessoaDTO.setTelefone(carroEntity.getEmpresa().getGerente().getTelefone());
            pessoaDTO.setSobrenome(carroEntity.getEmpresa().getGerente().getSobrenome());

            empresaDTO.setGerente(pessoaDTO);
            empresaDTO.setRazaoSocial(carroEntity.getEmpresa().getRazaoSocial());
            empresaDTO.setCnpj(carroEntity.getEmpresa().getCnpj());

            dtocarro.setEmpresaCarro(empresaDTO);
            dtocarro.setTipoCarro(carroEntity.getTipoCarro());
            dtocarro.setPlaca(carroEntity.getPlaca());

            return dtocarro;
        }).collect(Collectors.toList());
    }

    public List<CarroDTO> getCarroByEmpresa_Cnpj(@PathVariable(name = "id_empresa") String empresa_CNPJ){
        return carroRepository.findAllByEmpresa_Cnpj(empresa_CNPJ).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não foi encontrada!");
        }).stream().map(carroEntity1 -> {
            CarroDTO carroDTO = new CarroDTO();
            EmpresaDTO empresaDTO = new EmpresaDTO();
            PessoaDTO gerenteDTO = new PessoaDTO();

            gerenteDTO.setNome(carroEntity1.getEmpresa().getGerente().getNome());
            gerenteDTO.setSobrenome((carroEntity1.getEmpresa().getGerente().getSobrenome()));
            gerenteDTO.setCpf(carroEntity1.getEmpresa().getGerente().getCpf());
            gerenteDTO.setTelefone(carroEntity1.getEmpresa().getGerente().getTelefone());

            empresaDTO.setGerente(gerenteDTO);
            empresaDTO.setCnpj(carroEntity1.getEmpresa().getCnpj());
            empresaDTO.setRazaoSocial(carroEntity1.getEmpresa().getRazaoSocial());

            carroDTO.setEmpresaCarro(empresaDTO);
            carroDTO.setPlaca(carroEntity1.getPlaca());
            carroDTO.setEmpresaCarro(empresaDTO);

            return carroDTO;
        }).collect(Collectors.toList());
    }


    public void saveCarros(CarroAddDTO input) {
        EmpresaEntity newEmpresa = new EmpresaEntity();
        CarroEntity newCarro = new CarroEntity();
        newEmpresa.setIdEmpresa(input.getEmpresaCarro().getId());
        newCarro.setEmpresa(newEmpresa);
        newCarro.setTipoCarro(input.getTipoCarro());
        newCarro.setPlaca(input.getPlaca());
        carroRepository.save(newCarro);
    }

    public void deleteCarros(Long idEmpresa) {
        carroRepository.deleteById(idEmpresa);
    }

    public CarroUpDTO updateCarro(Long idcarronv, CarroUpDTO carroDTO) {
        EmpresaEntity empresaEntity = new EmpresaEntity();
        CarroEntity carroEntity = carroRepository.findById(idcarronv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrada!"));
        empresaEntity.setIdEmpresa(carroDTO.getEmpresaCarro().getId());
        carroEntity.setEmpresa(empresaEntity);
        carroEntity.setTipoCarro(carroDTO.getTipoCarro());
        carroEntity.setPlaca(carroDTO.getPlaca());
        carroRepository.save(carroEntity);
        return carroDTO;
    }
}

