package com.cesde.farmaciagrupo2.model.entity;

import com.cesde.farmaciagrupo2.model.base.BaseEntity;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
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
public class Formula extends BaseEntity {
 
    @Column(nullable = false, length = 100)
    private String nombre;
 
    @ManyToMany
    @JoinTable(
            name = "formula_medicamento",
            joinColumns = @JoinColumn(name = "formula_id"),
            inverseJoinColumns = @JoinColumn(name = "medicamento_id")
    )
    private Set<Medicamento> medicamentos;
}
