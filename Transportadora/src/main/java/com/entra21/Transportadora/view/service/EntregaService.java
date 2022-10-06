package com.entra21.Transportadora.view.service;
<<<<<<< HEAD
import com.entra21.Transportadora.model.dto.Entrega.EntregaAddDTO;
import com.entra21.Transportadora.model.dto.EntregaTrecho.EntregaTrechoDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.entity.EntregaEntity;
import com.entra21.Transportadora.view.repository.EntregaRepository;
import com.entra21.Transportadora.view.repository.EntregaTrechoRepository;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.repository.PessoaRepository;
=======
import com.entra21.Transportadora.model.entity.*;
import com.entra21.Transportadora.view.repository.*;
>>>>>>> 896f713429f68e3d28fd7c7542185f74e939ee58
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
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
    private TrechoRepository trechoRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EntregaTrechoRepository entregaTrechoRepository;
    @Autowired
    private CarroRepository carroRepository;

    public List<EntregaAddDTO> getAllEntrega() {
        return entregaRepository.findAll().stream().map(entregaEntity -> {
            EntregaAddDTO dtoentrega = new EntregaAddDTO();
            dtoentrega.setIdEntrega(entregaEntity.getIdEntrega());
            dtoentrega.setTipoEntrega(entregaEntity.getTipoEntrega());
            entregaEntity.getItens().stream().map(itemEntity -> {
                dtoentrega.getItemDTO().setIdItem(itemEntity.getIdItem());
                dtoentrega.getItemDTO().setLocalEntrega(itemEntity.getLocalEntrega());
                dtoentrega.getItemDTO().setLocalizador(itemEntity.getLocalizador());
                dtoentrega.getItemDTO().setNomeRecebedor(itemEntity.getNomeRecebedor());
                dtoentrega.getItemDTO().setStatus(itemEntity.getStatus());

                PessoaDTO pessoaDTO = new PessoaDTO();
                pessoaDTO.setIdPessoa(itemEntity.getPessoa().getIdPessoa());
                pessoaDTO.setNome(itemEntity.getPessoa().getNome());
                pessoaDTO.setSobrenome(itemEntity.getPessoa().getSobrenome());
                pessoaDTO.setCpf(itemEntity.getPessoa().getCpf());
                pessoaDTO.setTelefone(itemEntity.getPessoa().getTelefone());

                dtoentrega.getItemDTO().setPessoaItem(pessoaDTO);

                return null;
            });

            //MAP ENTREGA-TRECHO
                //MAP TRECHO
                //MAP CARRO

            //MAP ENTREGADOR
                //MAP SUPERVISOR
                //MAP EMPRESA



            EntregaTrechoDTO entregaTrechoDTO = new EntregaTrechoDTO();
            entregaTrechoDTO.setCompleto(entregaTrechoDTO.getCompleto());
            entregaTrechoDTO.setDataInicio(entregaTrechoDTO.getDataInicio());
            entregaTrechoDTO.setDataFim(entregaTrechoDTO.getDataFim());
            dtoentrega.setEntregaTrecho(entregaTrechoDTO);
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
            funcionarioDTO.setNome(entregaEntity.getEntregador().getNome());
            funcionarioDTO.setCpf(entregaEntity.getEntregador().getCpf());
            funcionarioDTO.setTelefone(entregaEntity.getEntregador().getTelefone());
            funcionarioDTO.setSobrenome(entregaEntity.getEntregador().getSobrenome());
            dtoentrega.setNomeEntregador(funcionarioDTO);
            return dtoentrega;
        }).collect(Collectors.toList());
    }

    public void save(EntregaAddDTO entregaDTO) {
        EntregaEntity entregaEntity = new EntregaEntity();

        entregaEntity.setTipoEntrega(entregaDTO.getTipoEntrega());

        Set<ItemEntity> itemEntities = entregaDTO.getItens().stream().map(itemDTO -> {
            ItemEntity itemEntity = new ItemEntity();
            if (itemDTO.getLocalizador() == null) {
                //generating UUID
                String UUId;
                do{
                    UUId = UUID.randomUUID().toString();
                }while (itemRepository.existsByLocalizador(UUId).orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não foi encontrado!");}));
                itemEntity.setLocalizador(UUId);
                itemEntity.setLocalEntrega(itemDTO.getLocalEntrega());
                itemEntity.setLocalizador(itemDTO.getLocalizador());
                itemEntity.setStatus("Esperando para postagem");
                itemEntity.setNomeRecebedor(itemDTO.getNomeRecebedor());

                if (itemDTO.getPessoaItem() != null){
                    PessoaEntity pessoaEntity = new PessoaEntity();
                    pessoaRepository.findByCpf(itemDTO.getPessoaItem().getCpf());
                    itemEntity.setPessoa(pessoaEntity);
                }
                return itemEntity;
            }
            itemEntity = itemRepository.findByLocalizador(itemDTO.getLocalizador()).orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não foi encontrado!");
            });
            return itemEntity;
        }).collect(Collectors.toSet());
        entregaEntity.setItens(itemEntities);

        FuncionarioEntity funcionarioEntity = funcionarioRepository.findByCpf(entregaDTO.getEntregador().getCpf()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não foi encontrado!");
        });
        if (funcionarioEntity.getCpf() == null) entregaEntity.setEntregador(null);
        else entregaEntity.setEntregador(funcionarioEntity);

        List<EntregaTrechoEntity> entregaTrechoEntities = entregaDTO.getEntregaTrecho().stream().map(entregaTrecho -> {
            EntregaTrechoEntity entregaTrechoEntity = new EntregaTrechoEntity();
            entregaTrechoEntity.setIdEntregaTrecho(entregaTrechoEntity.getIdEntregaTrecho());
            entregaTrechoEntity.setCompleto(false);

            TrechoEntity trechoEntity = new TrechoEntity();
            trechoEntity.setLocalInicio(entregaTrecho.getTrecho().getLocalFim());
            trechoEntity.setLocalFim(entregaTrecho.getTrecho().getLocalFim());
            trechoRepository.save(trechoEntity);
            entregaTrechoEntity.setTrecho(trechoEntity);

            CarroEntity carroEntity = carroRepository.findByPlaca(entregaTrecho.getCarro().getPlaca()).orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não foi encontrado!");
            });
            if (carroEntity.getPlaca() == null) entregaTrechoEntity.setCarro(null);
            else entregaTrechoEntity.setCarro(carroEntity);

            entregaTrechoEntity.setEntrega(entregaRepository.findById(1L).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrado!")
            ));

            entregaTrechoRepository.save(entregaTrechoEntity);
            return entregaTrechoEntity;
        }).collect(Collectors.toList());

        entregaEntity.setEntregaTrecho(entregaTrechoEntities);
        entregaRepository.save(entregaEntity);

        entregaEntity.setEntregaTrecho(entregaTrechoEntities);
        entregaTrechoEntities.stream().map(entregaTrechoEntity -> {
            entregaTrechoEntity.setIdEntregaTrecho(entregaEntity.getIdEntrega());
            entregaTrechoRepository.save(entregaTrechoEntity);
            return null;
        });
    }

    public void deleteEntrega(Long idEntrega) {
        entregaRepository.deleteById(idEntrega);
    }

    public EntregaPayloadDTO updateEntrega(Long idEntregaNv, EntregaPayloadDTO entregaAddDTO) {
        entregaRepository.findById(idEntregaNv).ifPresentOrElse((entregaEntity1) -> {
            entregaEntity1.setEntregador(
                funcionarioRepository.findByCpf(entregaAddDTO.getEntregador().getCpf()).orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não foi encontrado!");
                })
            );
            entregaRepository.save(entregaEntity1);
        }, () -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não foi encontrada!");});

        return entregaAddDTO;
    }
}
