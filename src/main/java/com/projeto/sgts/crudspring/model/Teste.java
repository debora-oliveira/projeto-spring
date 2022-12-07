package com.projeto.sgts.crudspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Teste {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String titulo;

    @Column(length = 30, nullable = false)
    private String sprint;

    @Column(length = 30, nullable = false)
    private String status;

    @Column(nullable = false)
    private String preCondicoes;

    
}
