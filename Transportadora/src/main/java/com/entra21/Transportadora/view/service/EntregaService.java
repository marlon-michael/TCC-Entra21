package com.entra21.Transportadora.view.service;
import com.entra21.Transportadora.model.dto.Entrega.EntregaDTO;
import com.entra21.Transportadora.model.dto.EntregaTrecho.EntregaTrechoDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.entity.EntregaEntity;
import com.entra21.Transportadora.view.repository.EntregaRepository;
import com.entra21.Transportadora.view.repository.EntregaTrechoRepository;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EntregaTrechoRepository trechoRepository;

    public void saveEntrega(EntregaDTO inputEntrega) {
        EntregaEntity newEntityentrega = new EntregaEntity();
        funcionarioRepository.findById(inputEntrega.getIdEntrega()).ifPresentOrElse(fE -> {
//        newEntityentrega.setIdEntrega(inputEntrega.getIdEntrega());
            newEntityentrega.setTipoEntrega(inputEntrega.getTipoEntrega());
            newEntityentrega.setEntregador(fE);
//        newEntityentrega.setEntregaTrecho(inputEntrega.getTipoEntrega());
            entregaRepository.save(newEntityentrega);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entregador não foi encontrado!");
        });
    }
    public void deleteEntrega(Long idEntrega) {
        entregaRepository.deleteById(idEntrega);
    }

    public List<EntregaDTO> getAllEntrega() {
        return entregaRepository.findAll().stream().map(er -> {
//            trechoRepository.findAll().stream().map(br -> {
            EntregaDTO dtoentrega = new EntregaDTO();
            dtoentrega.setIdEntrega(er.getIdEntrega());
            dtoentrega.setTipoEntrega(er.getTipoEntrega());
//            dtoentrega.setNomeEntregador();
//            dtoentrega.setNomeEntregador(er.getEntregador().getSobrenome());
//            dtoentrega.setNomeEntregador(er.getEntregador().getCpf());
//            dtoentrega.setNomeEntregador(er.getEntregador().getTelefone());
//            dtoentrega.setNomeEntregador(er.getEntregador().getEmpresa().getRazaoSocial());
            EntregaTrechoDTO entregaTrechoDTO = new EntregaTrechoDTO();
            entregaTrechoDTO.setCompleto(entregaTrechoDTO.getCompleto());
                entregaTrechoDTO.setDataInicio(entregaTrechoDTO.getDataInicio());
                entregaTrechoDTO.setDataFim(entregaTrechoDTO.getDataFim());

            dtoentrega.setEntregaTrecho(entregaTrechoDTO);


            PessoaDTO cr2 = new PessoaDTO();
            cr2.setNome(er.getEntregador().getNome());
            cr2.setCpf(er.getEntregador().getCpf());
            cr2.setTelefone(er.getEntregador().getTelefone());
            cr2.setSobrenome(er.getEntregador().getSobrenome());
            dtoentrega.setNomeEntregador(cr2);
            return dtoentrega;

//        }).collect(Collectors.toList());

    }).collect(Collectors.toList());
    }

//    public EntregaDTO updateEntrega(Long idEntregaNv, EntregaDTO entregaAddDTO) {
//        EntregaDTO entregaDTO = new EntregaDTO();
//        entregaRepository.findById(idEntregaNv).ifPresentOrElse(eE -> {
//            funcionarioRepository.findById(eE.getEntregador().getIdPessoa()).ifPresentOrElse(fE -> {
////        e.setIdEntrega(entregaDTO.getIdEntrega());
//                eE.setTipoEntrega(entregaAddDTO.getTipoEntrega());
//                eE.setEntregador(fE);
//                entregaDTO.setTipoEntrega(eE.getTipoEntrega());
//                entregaDTO.setNomeEntregador(eE.getEntregador().getNome());
//                entregaRepository.save(eE);
//            }, () -> {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entregador não foi encontrado!");
//            });
//        }, () -> {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entrega não foi encontrada!");
//        });
////            e.setIdEmpresa(empresaAddDTO.getIdEmpresa());;
//        return entregaDTO;
//    }
}
//        entregaAddDTO.setIdEntrega(e.getIdEntrega());
