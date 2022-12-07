package com.projeto.sgts.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.sgts.crudspring.model.Teste;
import com.projeto.sgts.crudspring.repository.TesteRepository;


@RestController
@RequestMapping("/api/testes")
public class TesteController {
    private final TesteRepository testeRepository;

    public TesteController(TesteRepository atividadeRepository) {
        this.testeRepository = atividadeRepository;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Teste> list(){
        return testeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teste> findById(@PathVariable Long id){
        return testeRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound ))
        .orElse(ResponseEntity.notFound().build());
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Teste> create(@RequestBody Teste teste){
        //System.out.println(atividade.getTipo());
        //return atividadeRepository.save(atividade);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(testeRepository.save(teste)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teste> update(@PathVariable Long id, @RequestBody Teste teste){
        return testeRepository.findById(id)
        .map(recordFound -> {
            recordFound.setTitulo(teste.getTitulo());                     
            recordFound.setSprint(teste.getSprint());
            recordFound.setStatus(teste.getStatus());
            recordFound.setPreCondicoes(teste.getPreCondicoes());
            Teste update = testeRepository.save(recordFound);
            return ResponseEntity.ok().body(update);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return testeRepository.findById(id)
                .map(recordFound -> {
                    testeRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
}
