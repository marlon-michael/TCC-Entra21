package com.entra21.Transportadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaRestController {

//    @Autowired
//    private GeneroService generoService;
//
//    @GetMapping
//    public List<GeneroDTO> getFranquias() {
//        return generoService.getAll1();
//    }
//
//    @PostMapping
//    public void addGenero(@RequestBody GeneroPayloadDTO newGenero) {
//        generoService.save1(newGenero);
//    }
//
//    @GetMapping("/{id}")
//    public GeneroDTO getGenero(@PathVariable(name = "id") Long id) {
//        return generoService.getById1(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteGenero(@PathVariable(name = "id") Long id) {
//        generoService.delete1(id);
//    }
//
//    @PutMapping("/{id}")
//    public GeneroDTO updateGenero(@PathVariable(name = "id") Long id,
//                                  @RequestBody String novoNome) {
//        return generoService.update1(id, novoNome);
//    }
}