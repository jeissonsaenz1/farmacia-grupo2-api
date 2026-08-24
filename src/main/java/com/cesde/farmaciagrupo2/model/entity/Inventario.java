package com.cesde.farmaciagrupo2.model.entity;

import com.cesde.farmaciagrupo2.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Inventario extends BaseEntity {

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, length = 50)
    private String lote;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    @OneToOne
    @JoinColumn(
        name = "medicamento_id",
        nullable = false,
        unique = true
    )
    private Medicamento medicamento;
}
