package com.projeto.sgts.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.sgts.crudspring.model.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
    
}
