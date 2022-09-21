//package com.entra21.Transportadora.view.service;
//import com.entra21.Transportadora.model.dto.EntregaDTO;
//import com.entra21.Transportadora.model.entity.EntregaEntity;
//import com.entra21.Transportadora.view.repository.EntregaRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class EntregaService {
//    @Autowired
//    private EntregaRepository entregaRepository;
//
//    public void saveEntrega(EntregaDTO inputEntrega) {
//        EntregaEntity newEntityentrega = new EntregaEntity();
//        newEntityentrega.setIdEntrega(inputEntrega.getIdEntrega());
//        newEntityentrega.setTipoEntrega(inputEntrega.getTipoEntrega());
//        newEntityentrega.setEntregador(inputEntrega.getEntregador());
//        entregaRepository.save(newEntityentrega);
//    }
//
//    public void deleteEntrega(Long idEntrega) {
//        entregaRepository.deleteById(idEntrega);
//    }
//
//    public List<EntregaDTO> getAllEntrega() {
//        return entregaRepository.findAll().stream().map(er -> {
//            EntregaDTO dtoentrega = new EntregaDTO();
//            dtoentrega.setIdEntrega(er.getIdEntrega());
//            dtoentrega.setTipoEntrega(er.getTipoEntrega());
//            dtoentrega.setEntregador(er.getEntregador());
//            return dtoentrega;
//        }).collect(Collectors.toList());
//    }
//
//
//    public EntregaDTO updateEntrega(Long idEmpresanv, EntregaDTO entregaDTO) {
//        EntregaEntity e = entregaRepository.findById(idEmpresanv).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrada!"));
//        e.setIdEntrega(entregaDTO.getIdEntrega());
//        e.setTipoEntrega(entregaDTO.getTipoEntrega());
//        e.setEntregador(entregaDTO.getEntregador());
//        e = entregaRepository.save(e);
//        entregaDTO.setIdEntrega(e.getIdEntrega());
//        return entregaDTO;
//    }
//
//}
