package com.projeto.sgts.crudspring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.sgts.crudspring.model.Teste;


@Repository
public interface TesteRepository extends JpaRepository<Teste, Long>{
    
}
