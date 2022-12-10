package com.entra21.Transportadora.controller;


import com.entra21.Transportadora.model.dto.PessoaDTO;
import com.entra21.Transportadora.view.repository.EmpresaRepository;
import com.entra21.Transportadora.view.repository.FuncionarioRepository;
import com.entra21.Transportadora.view.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/pessoa")
public class PessoaRestController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private FuncionarioRepository funcionarioService;

    @Autowired
    private EmpresaRepository empresaService;
    

    @GetMapping
    public List<PessoaDTO> getPessoas() {
        return pessoaService.getAll();
    }

    @PostMapping("/login")
    public PessoaDTO getLogin(@RequestBody PessoaDTO login) {
        PessoaDTO pessoaDTO = new PessoaDTO(pessoaService.buscarLogin(login));

        if (empresaService.findByGerente_Cpf(pessoaDTO.getCpf()).isPresent()){
            pessoaDTO.setCargo("GERENTE");
            return pessoaDTO;
        }
        else if(funcionarioService.findByCpf(pessoaDTO.getCpf()).isPresent()){
            pessoaDTO.setCargo("FUNCIONARIO");
            if(funcionarioService.findBySupervisor_Cpf(pessoaDTO.getCpf()).isPresent()){
                if (!funcionarioService.findBySupervisor_Cpf(pessoaDTO.getCpf()).get().isEmpty()){
                    pessoaDTO.setCargo("SUPERVISOR");
                }
            }
            return pessoaDTO;
        }
        else {
            pessoaDTO.setCargo("PESSOA");
            return pessoaDTO;
        }
    }

    @GetMapping("/{cpf}")
    public PessoaDTO getAllByCpf(@PathVariable(name = "cpf") String cpf) {
        return pessoaService.findByCpf(cpf);
    }

    @PostMapping("/cadastro")
    public void addPessoa(@RequestBody PessoaDTO newPessoa) {
        pessoaService.save(newPessoa);
    }

    @PutMapping("/{cpf}")
    public void updatePessoa(@PathVariable(name = "cpf") String cpf, @RequestBody PessoaDTO pessoaPayLoadDTO) {
        pessoaService.updatePessoa(cpf, pessoaPayLoadDTO);
    }

}
