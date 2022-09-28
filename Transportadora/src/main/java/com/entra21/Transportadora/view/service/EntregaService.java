package com.entra21.Transportadora.view.service;
import com.entra21.Transportadora.model.dto.*;
import com.entra21.Transportadora.model.entity.*;
import com.entra21.Transportadora.view.repository.EntregaRepository;
import com.entra21.Transportadora.view.repository.EntregaTrechoRepository;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.EOFException;
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
            newEntityentrega.setTipoEntrega(inputEntrega.getTipoEntrega());
//            newEntityentrega.setEntregador(inputEntrega);
//        newEntityentrega.setEntregaTrecho(inputEntrega.getTipoEntrega());
            entregaRepository.save(newEntityentrega);
        }

//        CarroEntity newEntity = new CarroEntity();
//        newEntity.setIdCarro(input.getIdCarro());
//        newEntity.setTipoCarro(input.getTipoCarro());
//        newEntity.setPlaca(input.getPlaca());
////       newEntity.setEmpresa(input.getEmpresaCarro());
//        carroRepository.save(newEntity);
//    }


    public void deleteEntrega(Long idEntrega) {
        entregaRepository.deleteById(idEntrega);
    }

    public List<EntregaDTO> getAllEntrega() {
        return entregaRepository.findAll().stream().map(er -> {
//            trechoRepository.findAll().stream().map(br -> {
            EntregaDTO dtoentrega = new EntregaDTO();
            dtoentrega.setIdEntrega(er.getIdEntrega());
            dtoentrega.setTipoEntrega(er.getTipoEntrega());

//            FuncionarioDTO cr3 = new FuncionarioDTO();
//            dtoentrega.setNomeEntregador(cr3.getSupervisorFuncionario());

            FuncionarioPayLoadDTO cr2 = new FuncionarioPayLoadDTO();
            cr2.setNome(er.getEntregador().getNome());
            cr2.setSobrenome(er.getEntregador().getSobrenome());
            cr2.setCpf(er.getEntregador().getCpf());
            cr2.setTelefone(er.getEntregador().getTelefone());
            cr2.setIdFuncionario(er.getEntregador().getIdPessoa());
            dtoentrega.setNomeEntregador(cr2);


//            if (er.getEntregaTrecho() == null) {
//                return dtoentrega;
//            } else {
//                dtoentrega.setEntregaTrecho(entregaTrechoDTO);
//                return dtoentrega;
//            }

            dtoentrega.setEntregaTrecho(er.getEntregaTrecho().stream().map(et -> {
                EntregaTrechoDTO entregaTrechoDTO = new EntregaTrechoDTO();
                entregaTrechoDTO.setCompleto(et.getCompleto());
                entregaTrechoDTO.setDataInicio(et.getDataInicio());
                entregaTrechoDTO.setDataFim(et.getDataFim());
                return entregaTrechoDTO;
            }).collect(Collectors.toList()));
            return dtoentrega;
        }).collect(Collectors.toList());


    }

    public EntregaDTO updateEntrega(Long idEntregaNv, EntregaDTO entregaDTO) {
        EntregaEntity e = entregaRepository.findById(idEntregaNv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada!"));
        e.setTipoEntrega(entregaDTO.getTipoEntrega());


        //FAZER UMA LISTA
//        EntregaTrechoEntity entregaTrechoEntity = new EntregaTrechoEntity();
//        entregaTrechoEntity.setCompleto(entregaDTO.getEntregaTrecho().getCompleto());
//        entregaTrechoEntity.setDataInicio(entregaDTO.getEntregaTrecho().getDataInicio());
//        entregaTrechoEntity.setDataFim(entregaDTO.getEntregaTrecho().getDataFim());



        FuncionarioEntity ent2 = new FuncionarioEntity();
        ent2.setIdPessoa(entregaDTO.getNomeEntregador().getIdFuncionario());
        e.setEntregador(ent2);
        e = entregaRepository.save(e);
        return entregaDTO;
    }
}
//        e.setEntregaTrecho(entregaTrechoEntity.);
//        entregaTrechoEntity.setCarro(entregaDTO.getEntregaTrecho().get);
//        entregaTrechoEntity.setTrecho(entregaDTO.getEntregaTrecho().get);
//        entregaTrechoEntity.setEntrega(entregaDTO.getEntregaTrecho().get);

//                EmpresaEntity ent = new EmpresaEntity();
//                ent.setIdEmpresa(carroDTO.getEmpresaCarro().getIdEmpresa());
//                e.setEmpresa(ent);
//                e = carroRepository.save(e);

//        entregaAddDTO.setIdEntrega(e.getIdEntrega());

//
//    CarroEntity e = carroRepository.findById(idcarronv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrada!"));
//        e.setTipoCarro(carroDTO.getTipoCarro());
//                e.setPlaca(carroDTO.getPlaca());
//                EmpresaEntity ent = new EmpresaEntity();
//                ent.setIdEmpresa(carroDTO.getEmpresaCarro().getIdEmpresa());
//                e.setEmpresa(ent);
//                e = carroRepository.save(e);
//



//   entregaRepository.findById(idEntregaNv).ifPresentOrElse(eE -> {
//            funcionarioRepository.findById(eE.getEntregador().getIdPessoa()).ifPresentOrElse(fE -> {
////        e.setIdEntrega(entregaDTO.getIdEntrega());
//                eE.setTipoEntrega(entregaDTO.getTipoEntrega());
//                EntregaTrechoDTO entregaTrechoDTO = new EntregaTrechoDTO();
//                entregaTrechoDTO.setCompleto(entregaDTO.getEntregaTrecho().getCompleto());
//                entregaTrechoDTO.setDataInicio(entregaDTO.getEntregaTrecho().getDataInicio());
//                entregaTrechoDTO.setDataFim(entregaDTO.getEntregaTrecho().getDataFim());
//
//                entregaRepository.save(eE);
//
//            }, () -> {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entregador não foi encontrado!");
//            });
//        }, () -> {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entrega não foi encontrada!");
//        });
////            e.setIdEmpresa(empresaAddDTO.getIdEmpresa());;