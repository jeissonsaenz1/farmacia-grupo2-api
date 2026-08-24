package com.cesde.farmaciagrupo2.model.entity;

import com.cesde.farmaciagrupo2.model.base.BaseEntity;
 
import com.cesde.farmaciagrupo2.model.embeddable.Direccion;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
public class Laboratorio extends BaseEntity {
 
    @Column(nullable = false, length = 100)
    private String nombre;
 
    @Embedded
    private Direccion direccion;
 
    @OneToMany(mappedBy = "laboratorio")
    private List<Medicamento> medicamentos;
}
