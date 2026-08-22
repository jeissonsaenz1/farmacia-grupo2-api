package com.cesde.farmacia_grupo2.model.entity;

import com.cesde.farmacia_grupo2.model.base.BaseEntity;

import com.cesde.farmacia_grupo2.model.enums.EstadoVenta;
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
 
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Venta extends BaseEntity {
 
    @Column(nullable = false)
    private Double total;
 
    @Enumerated(EnumType.STRING)
    private EstadoVenta estado;
 
    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> detalles;
}