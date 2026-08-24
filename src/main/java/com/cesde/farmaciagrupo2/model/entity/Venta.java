package com.cesde.farmaciagrupo2.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
 
import java.util.List;

import com.cesde.farmaciagrupo2.model.base.BaseEntity;
import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;
 
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Venta extends BaseEntity {
 
    @Column(nullable = false, precision = 10, scale = 2)
    private Double total;  
 
    @Enumerated(EnumType.STRING)
    private EstadoVenta estado;
 
    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalles;
}