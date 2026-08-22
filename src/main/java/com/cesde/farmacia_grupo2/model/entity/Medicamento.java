package com.cesde.farmacia_grupo2.model.entity;

import com.cesde.farmacia_grupo2.model.base.BaseEntity;
 
import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.ManyToMany;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;

import lombok.experimental.SuperBuilder;
 
import java.util.Set;
 
@Entity

@Getter

@Setter

@NoArgsConstructor

@AllArgsConstructor

@SuperBuilder

public class Medicamento extends BaseEntity {
 
    @Column(nullable = false, length = 100)

    private String nombre;
 
    @Column(nullable = false)

    private Double precio;
 
    @ManyToOne

    @JoinColumn(name = "laboratorio_id")

    private Laboratorio laboratorio;
 
    @ManyToMany(mappedBy = "medicamentos")

    private Set<Formula> formulas;

}

 
