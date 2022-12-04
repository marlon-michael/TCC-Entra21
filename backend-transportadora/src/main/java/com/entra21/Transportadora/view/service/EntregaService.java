package com.entra21.Transportadora.view.service;

import com.entra21.Transportadora.model.entity.*;
import com.entra21.Transportadora.view.repository.EntregaRepository;
import com.entra21.Transportadora.view.repository.EntregaTrechoRepository;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.model.dto.Carro.CarroDTO;
import com.entra21.Transportadora.model.dto.Empresa.EmpresaDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaAddDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaDTO;
import com.entra21.Transportadora.model.dto.Entrega.EntregaUpDTO;
import com.entra21.Transportadora.model.dto.EntregaTrecho.EntregaTrechoDTO;
import com.entra21.Transportadora.model.dto.Funcionario.FuncionarioDTO;
import com.entra21.Transportadora.model.dto.Item.ItemDTO;
import com.entra21.Transportadora.model.dto.Pessoa.PessoaDTO;
import com.entra21.Transportadora.model.dto.Trecho.TrechoDTO;
import com.entra21.Transportadora.view.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;
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


    //FAZER UM METODO PARA CONVERTER ENTITY PARA DTO
    //FAZER UM METODO PARA CONVERTER DTO EM ENTITY


    public List<EntregaEntity> test() {
        return entregaRepository.findAll();
    }

    public List<EntregaDTO> getAllEntrega() {
        return entregaRepository.findAll().stream().map(entregaEntity -> {
            EntregaDTO dtoentrega = new EntregaDTO();
            dtoentrega.setTipoEntrega(entregaEntity.getTipoEntrega());
            dtoentrega.setItens(
                entregaEntity.getItens().stream().map(itemEntity -> {
                    ItemDTO itemDTO = new ItemDTO();
                    if (itemEntity.getPessoa() != null){
                        PessoaDTO pessoaDTO = new PessoaDTO();
                        pessoaDTO.setNome(itemEntity.getPessoa().getNome());
                        pessoaDTO.setSobrenome(itemEntity.getPessoa().getSobrenome());
                        pessoaDTO.setCpf(itemEntity.getPessoa().getCpf());
                        pessoaDTO.setTelefone(itemEntity.getPessoa().getTelefone());
                        itemDTO.setPessoaItem(pessoaDTO);
                    }
                    itemDTO.setLocalEntrega(itemEntity.getLocalEntrega());
                    itemDTO.setLocalizador(itemEntity.getLocalizador());
                    itemDTO.setNomeRecebedor(itemEntity.getNomeRecebedor());
                    itemDTO.setStatus(itemEntity.getStatus());
                    return itemDTO;
                }).collect(Collectors.toList())
            );

            dtoentrega.setEntregador(new FuncionarioDTO());
            dtoentrega.getEntregador().setCpf(entregaEntity.getEntregador().getCpf());
            dtoentrega.getEntregador().setNome(entregaEntity.getEntregador().getNome());
            dtoentrega.getEntregador().setSobrenome(entregaEntity.getEntregador().getSobrenome());
            dtoentrega.getEntregador().setTelefone(entregaEntity.getEntregador().getTelefone());

            dtoentrega.getEntregador().setEmpresa(new EmpresaDTO());

            dtoentrega.getEntregador().getEmpresa().setGerente(new PessoaDTO());
            dtoentrega.getEntregador().getEmpresa().getGerente().setNome(entregaEntity.getEntregador().getEmpresa().getGerente().getNome());
            dtoentrega.getEntregador().getEmpresa().getGerente().setSobrenome(entregaEntity.getEntregador().getEmpresa().getGerente().getSobrenome());
            dtoentrega.getEntregador().getEmpresa().getGerente().setCpf(entregaEntity.getEntregador().getEmpresa().getGerente().getCpf());
            dtoentrega.getEntregador().getEmpresa().getGerente().setTelefone(entregaEntity.getEntregador().getEmpresa().getGerente().getTelefone());

            dtoentrega.getEntregador().getEmpresa().setRazaoSocial(entregaEntity.getEntregador().getEmpresa().getRazaoSocial());
            dtoentrega.getEntregador().getEmpresa().setCnpj(entregaEntity.getEntregador().getEmpresa().getCnpj());

//            dtoentrega.getEntregador().setSupervisor(new FuncionarioDTO());
//            dtoentrega.getEntregador().getSupervisor().setNome(entregaEntity.getEntregador().getSupervisor().getNome());
//            dtoentrega.getEntregador().getSupervisor().setSobrenome(entregaEntity.getEntregador().getSupervisor().getSobrenome());
//            dtoentrega.getEntregador().getSupervisor().setCpf(entregaEntity.getEntregador().getSupervisor().getCpf());
//            dtoentrega.getEntregador().getSupervisor().setTelefone(entregaEntity.getEntregador().getSupervisor().getTelefone());

            dtoentrega.setEntregaTrecho(entregaEntity.getEntregaTrecho().stream().map(entregaTrecho -> {
                EntregaTrechoDTO entregaTrechoDTO = new EntregaTrechoDTO();

                TrechoDTO trechoDTO = new TrechoDTO();
                trechoDTO.setLocalInicio(entregaTrecho.getTrecho().getLocalInicio());
                trechoDTO.setLocalFim(entregaTrecho.getTrecho().getLocalFim());
                entregaTrechoDTO.setTrecho(trechoDTO);

                CarroDTO carroDTO = new CarroDTO();
                carroDTO.setPlaca(entregaTrecho.getCarro().getPlaca());
                carroDTO.setTipoCarro(entregaTrecho.getCarro().getTipoCarro());
                carroDTO.setEmpresaCarro(new EmpresaDTO());
                carroDTO.getEmpresaCarro().setRazaoSocial(entregaTrecho.getCarro().getEmpresa().getRazaoSocial());
                carroDTO.getEmpresaCarro().setCnpj(entregaTrecho.getCarro().getEmpresa().getCnpj());
                entregaTrechoDTO.setCarro(carroDTO);

                entregaTrechoDTO.setCompleto(entregaTrecho.getCompleto());
                entregaTrechoDTO.setDataInicio(entregaTrecho.getDataInicio());
                entregaTrechoDTO.setDataFim(entregaTrecho.getDataFim());

                return entregaTrechoDTO;
            }).collect(Collectors.toList()));

            return dtoentrega;
        }).collect(Collectors.toList());
    }

    public List<EntregaDTO> getAllEntragaByEntregador(String cpf){
        return entregaRepository.findAllByEntregador_Cpf(cpf).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF/Entregador/Entrega não encontrado");
        }).stream().map(entregaEntity -> {
            EntregaDTO dtoentrega = new EntregaDTO();
            dtoentrega.setIdEntrega(entregaEntity.getIdEntrega());
            dtoentrega.setTipoEntrega(entregaEntity.getTipoEntrega());
            dtoentrega.setItens(
                entregaEntity.getItens().stream().map(itemEntity -> {
                    ItemDTO itemDTO = new ItemDTO();

                    itemDTO.setPessoaItem(null);
                    if (itemEntity.getPessoa() != null){
                        PessoaDTO pessoaDTO = new PessoaDTO();
                        pessoaDTO.setNome(itemEntity.getPessoa().getNome());
                        pessoaDTO.setSobrenome(itemEntity.getPessoa().getSobrenome());
                        pessoaDTO.setCpf(itemEntity.getPessoa().getCpf());
                        pessoaDTO.setTelefone(itemEntity.getPessoa().getTelefone());
                        itemDTO.setPessoaItem(pessoaDTO);
                    }

                    itemDTO.setLocalEntrega(itemEntity.getLocalEntrega());
                    itemDTO.setLocalizador(itemEntity.getLocalizador());
                    itemDTO.setNomeRecebedor(itemEntity.getNomeRecebedor());
                    itemDTO.setStatus(itemEntity.getStatus());

                    return itemDTO;
                }).collect(Collectors.toList())
            );

            dtoentrega.setEntregaTrecho(entregaEntity.getEntregaTrecho().stream().map(entregaTrecho -> {
                EntregaTrechoDTO entregaTrechoDTO = new EntregaTrechoDTO();

                TrechoDTO trechoDTO = new TrechoDTO();
                trechoDTO.setLocalInicio(entregaTrecho.getTrecho().getLocalInicio());
                trechoDTO.setLocalFim(entregaTrecho.getTrecho().getLocalFim());
                entregaTrechoDTO.setTrecho(trechoDTO);

                CarroDTO carroDTO = new CarroDTO();
                carroDTO.setPlaca(entregaTrecho.getCarro().getPlaca());
                carroDTO.setTipoCarro(entregaTrecho.getCarro().getTipoCarro());
                carroDTO.setEmpresaCarro(new EmpresaDTO());
                carroDTO.getEmpresaCarro().setRazaoSocial(entregaTrecho.getCarro().getEmpresa().getRazaoSocial());
                carroDTO.getEmpresaCarro().setCnpj(entregaTrecho.getCarro().getEmpresa().getCnpj());

                entregaTrechoDTO.setCarro(carroDTO);
                entregaTrechoDTO.setCompleto(entregaTrecho.getCompleto());
                entregaTrechoDTO.setDataInicio(entregaTrecho.getDataInicio());
                entregaTrechoDTO.setDataFim(entregaTrecho.getDataFim());

                return entregaTrechoDTO;
            }).collect(Collectors.toList()));

            return dtoentrega;
        }).collect(Collectors.toList());
    }

    public void save(EntregaAddDTO entregaDTO) {
        EntregaEntity entregaEntity = new EntregaEntity();
        entregaEntity.setTipoEntrega(entregaDTO.getTipoEntrega());
        List<ItemEntity> itemEntities = new ArrayList<>();
        itemEntities = entregaDTO.getItens().stream().map(itemDTO -> {
            return itemRepository.findByLocalizador(itemDTO.getLocalizador()).orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item não foi encontrado!");
            });
        }).collect(Collectors.toList());
        if (itemEntities == null || itemEntities.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Itens a entregar não pode estar vazio!");
        entregaEntity.setItens(itemEntities);
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findByCpf(entregaDTO.getEntregador().getCpf()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionario não foi encontrado!");
        });
        if (funcionarioEntity.getCpf() == null) entregaEntity.setEntregador(null);
        else entregaEntity.setEntregador(funcionarioEntity);
        List<EntregaTrechoEntity> entregaTrechoEntities = entregaDTO.getEntregaTrecho().stream().map(entregaTrecho -> {
            EntregaTrechoEntity entregaTrechoEntity = new EntregaTrechoEntity();
            entregaTrechoEntity.setIdEntregaTrecho(entregaTrechoEntity.getIdEntregaTrecho());
            entregaTrechoEntity.setCompleto(false);
            TrechoEntity trechoEntity = new TrechoEntity();
            trechoEntity.setLocalInicio(entregaTrecho.getTrecho().getLocalInicio());
            trechoEntity.setLocalFim(entregaTrecho.getTrecho().getLocalFim());
            trechoRepository.save(trechoEntity);
            entregaTrechoEntity.setTrecho(trechoEntity);
            CarroEntity carroEntity = carroRepository.findByPlaca(entregaTrecho.getCarro().getPlaca()).orElseThrow(
                    ()->{throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionario não foi encontrado!");});
            entregaTrechoEntity.setCarro(carroEntity);
            entregaTrechoEntity.setEntrega(entregaRepository.findById(1L).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entrega não encontrado!")));
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

    //TODO: NÃO DEVE SER POSSIVEL DELETAR ENTREGAS
    public void deleteEntrega(Long idEntrega) {
        entregaRepository.deleteById(idEntrega);
    }


    //TODO: ENCONTRAR UM JEITO DO USUARIO ENCONTRAR A ENTREGA/ENTREGA_TRECHO (LOCALIZADOR DE ENTREGA???)
    public EntregaUpDTO updateEntrega(Long idEntrega, EntregaUpDTO entregaDTO) {
        entregaRepository.findById(idEntrega).ifPresentOrElse((entregaEntity) -> {
            entregaEntity.setEntregador(
                funcionarioRepository.findByCpf(entregaDTO.getEntregador().getCpf()).orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Funcionario não foi encontrado!");
                })
            );
            entregaRepository.save(entregaEntity);
        }, () -> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entrega não foi encontrada!");});

        return entregaDTO;
    }
}

