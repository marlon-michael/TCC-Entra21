package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.dto.CarroDTO;
import com.entra21.Transportadora.model.dto.EmpresaDTO;
import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.model.entity.CarroEntity;
import com.entra21.Transportadora.model.entity.EmpresaEntity;
import com.entra21.Transportadora.view.repository.CarroRepository;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
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

    @Autowired
    private EmpresaRepository empresaRepository;

    public CarroDTO getCarroByPlaca(String placa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        CarroDTO carroDTO = new CarroDTO();
        CarroEntity carroEntity = carroRepository.findByPlaca(placa).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro/Placa não encontrado");});

        pessoaDTO.setNome(carroEntity.getEmpresa().getGerente().getNome());
        pessoaDTO.setCpf(carroEntity.getEmpresa().getGerente().getCpf());
        pessoaDTO.setTelefone(carroEntity.getEmpresa().getGerente().getTelefone());
        pessoaDTO.setSobrenome(carroEntity.getEmpresa().getGerente().getSobrenome());

        empresaDTO.setGerente(pessoaDTO);
        empresaDTO.setRazaoSocial(carroEntity.getEmpresa().getRazaoSocial());
        empresaDTO.setCnpj(carroEntity.getEmpresa().getCnpj());

        carroDTO.setEmpresa(empresaDTO);
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

            dtocarro.setEmpresa(empresaDTO);
            dtocarro.setTipoCarro(carroEntity.getTipoCarro());
            dtocarro.setPlaca(carroEntity.getPlaca());

            return dtocarro;
        }).collect(Collectors.toList());
    }

    public List<CarroDTO> getCarroByEmpresa_Cnpj(@PathVariable(name = "id_empresa") String empresa_CNPJ){
        return carroRepository.findAllByEmpresa_Cnpj(empresa_CNPJ).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CNPJ/Empresa/Carro não foi encontrado!");
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

            carroDTO.setEmpresa(empresaDTO);
            carroDTO.setPlaca(carroEntity1.getPlaca());
            carroDTO.setTipoCarro(carroEntity1.getTipoCarro());

            return carroDTO;
        }).collect(Collectors.toList());
    }

    public void saveCarros(CarroDTO input) {
        CarroEntity newCarro = new CarroEntity();
        EmpresaEntity empresaEntity = empresaRepository.findByCnpj(input.getEmpresa().getCnpj()).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa/CNPJ/Carro não encontrado");});
        newCarro.setEmpresa(empresaEntity);
        newCarro.setTipoCarro(input.getTipoCarro());
        newCarro.setPlaca(input.getPlaca());
        try {
            carroRepository.save(newCarro);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "dados invalidos");
        }
    }

    public void deleteCarros(String placa) {
        carroRepository.deleteById(
            carroRepository.findByPlaca(placa).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Placa/Carro não encontrado");}).getIdCarro()
        );
    }

    public void updateCarro(String placa, CarroDTO carroDTO) {
        EmpresaEntity empresaEntity = empresaRepository.findByCnpj(carroDTO.getEmpresa().getCnpj()).orElseThrow(() -> {{throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa/CNPJ não encontrado");}});
        CarroEntity carroEntity = carroRepository.findByPlaca(placa).orElseThrow(()->{throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro/Placa não encontrada!");});
        empresaEntity.setIdEmpresa(empresaEntity.getIdEmpresa());
        carroEntity.setEmpresa(empresaEntity);
        carroEntity.setTipoCarro(carroDTO.getTipoCarro());
        carroEntity.setPlaca(carroDTO.getPlaca());
        carroRepository.save(carroEntity);
    }
}

