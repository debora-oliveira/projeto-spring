package com.projeto.sgts.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.sgts.crudspring.model.Atividade;
import com.projeto.sgts.crudspring.repository.AtividadeRepository;


@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    private final AtividadeRepository atividadeRepository;
       

    public AtividadeController(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Atividade> list(){
        return atividadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> findById(@PathVariable Long id){
        return atividadeRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound ))
        .orElse(ResponseEntity.notFound().build());
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Atividade> create(@RequestBody Atividade atividade){
        //System.out.println(atividade.getTipo());
        //return atividadeRepository.save(atividade);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(atividadeRepository.save(atividade)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> update(@PathVariable Long id, @RequestBody Atividade atividade){
        return atividadeRepository.findById(id)
        .map(recordFound -> {
            recordFound.setTipo(atividade.getTipo());
            recordFound.setResumo(atividade.getResumo());
            recordFound.setSprint(atividade.getSprint());
            recordFound.setStatus(atividade.getStatus());
            Atividade update = atividadeRepository.save(recordFound);
            return ResponseEntity.ok().body(update);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return atividadeRepository.findById(id)
                .map(recordFound -> {
                    atividadeRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
