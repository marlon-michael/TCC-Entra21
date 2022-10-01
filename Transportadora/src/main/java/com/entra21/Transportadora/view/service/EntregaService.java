package com.entra21.Transportadora.view.service;
import com.entra21.Transportadora.model.dto.*;
import com.entra21.Transportadora.model.entity.*;
import com.entra21.Transportadora.view.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
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
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EntregaTrechoRepository entregaTrechoRepository;
    @Autowired
    private CarroRepository carroRepository;

    public List<Long> save(EntregaPayloadDTO entregaDTO) {
        EntregaEntity entregaEntity = new EntregaEntity();

        Set<ItemEntity> itemEntities = entregaDTO.getItens().stream().map(itemDTO -> {
            ItemEntity itemEntity = new ItemEntity();
            if (itemDTO.getLocalizador() == null) {
                itemEntity.setLocalEntrega(itemDTO.getLocalEntrega());
                itemEntity.setLocalizador(itemDTO.getLocalizador());
                itemEntity.setStatus("Esperando para postagem");
                itemEntity.setNomeRecebedor("REMOVER COLUNA \"NOME RECEBEDOR\"");

                if (itemDTO.getPessoaItem() != null){
                    PessoaEntity pessoaEntity = new PessoaEntity();
                    pessoaRepository.findByCpf(itemDTO.getPessoaItem().getCpf());
                    itemEntity.setPessoa(pessoaEntity);
                }
            }
            itemEntity = itemRepository.findByLocalizador(itemDTO.getLocalizador());
//            if (itemEntity.getLocalizador() == null) return null;
            return itemEntity;
        }).collect(Collectors.toSet());
        entregaEntity.setItens(itemEntities);

        FuncionarioEntity funcionarioEntity = funcionarioRepository.findByCpf(entregaDTO.getEntregador().getCpf());
        if (funcionarioEntity.getCpf() == null) entregaEntity.setEntregador(null);
        entregaEntity.setEntregador(funcionarioEntity);

        entregaEntity.setTipoEntrega(entregaDTO.getTipoEntrega());

        List<EntregaTrechoEntity> entregaTrechoEntities = entregaDTO.getEntregaTrecho().stream().map(entregaTrecho -> {
            EntregaTrechoEntity entregaTrechoEntity = new EntregaTrechoEntity();
            entregaTrechoEntity.setIdEntregaTrecho(entregaTrechoEntity.getIdEntregaTrecho());
            entregaTrechoEntity.setCompleto(false);

            TrechoEntity trechoEntity = new TrechoEntity();
            trechoEntity.setLocalInicio(entregaTrecho.getTrecho().getLocalFim());
            trechoEntity.setLocalFim(entregaTrecho.getTrecho().getLocalFim());
            entregaTrechoEntity.setTrecho(trechoEntity);

            CarroEntity carroEntity = carroRepository.findByPlaca(entregaTrecho.getCarro().getPlaca());
            if (carroEntity.getPlaca() == null) entregaTrechoEntity.setCarro(null);
            else entregaTrechoEntity.setCarro(carroEntity);

            entregaTrechoEntity.setEntrega(entregaRepository.findById(1L).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrado!")));

            return entregaTrechoEntity;
        }).collect(Collectors.toList());

        entregaEntity.setEntregaTrecho(entregaTrechoEntities);
        return entregaTrechoEntities.stream().map(entregaTrechoEntity -> {
            return entregaTrechoEntity.getIdEntregaTrecho();
        }).collect(Collectors.toList());

//        entregaRepository.save(entregaEntity);

        //update entrega from entregaTrecho
    }

    public void deleteEntrega(Long idEntrega) {
        entregaRepository.deleteById(idEntrega);
    }

    public List<EntregaDTO> getAllEntrega() {
        return entregaRepository.findAll().stream().map(er -> {
            EntregaDTO dtoentrega = new EntregaDTO();
            dtoentrega.setIdEntrega(er.getIdEntrega());
            dtoentrega.setTipoEntrega(er.getTipoEntrega());
            EntregaTrechoDTO entregaTrechoDTO = new EntregaTrechoDTO();
            entregaTrechoDTO.setCompleto(entregaTrechoDTO.getCompleto());
            entregaTrechoDTO.setDataInicio(entregaTrechoDTO.getDataInicio());
            entregaTrechoDTO.setDataFim(entregaTrechoDTO.getDataFim());
            dtoentrega.setEntregaTrecho(entregaTrechoDTO);
            FuncionarioDTO cr2 = new FuncionarioDTO();
            cr2.setNome(er.getEntregador().getNome());
            cr2.setCpf(er.getEntregador().getCpf());
            cr2.setTelefone(er.getEntregador().getTelefone());
            cr2.setSobrenome(er.getEntregador().getSobrenome());
            dtoentrega.setNomeEntregador(cr2);
            return dtoentrega;
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
