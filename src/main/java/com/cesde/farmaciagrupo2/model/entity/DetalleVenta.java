package com.cesde.farmaciagrupo2.model.entity;

import com.cesde.farmaciagrupo2.model.base.BaseEntity;
 
import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;

import lombok.experimental.SuperBuilder;
 
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity

@Getter

@Setter

@NoArgsConstructor

@AllArgsConstructor

@SuperBuilder

public class DetalleVenta extends BaseEntity {
 
    @Column(nullable = false)

    private Integer cantidad;
 
    @Column(nullable = false)

    private Double subtotal;
 
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;
 
    @ManyToOne

    @JoinColumn(name = "medicamento_id")

    private Medicamento medicamento;

}

 
