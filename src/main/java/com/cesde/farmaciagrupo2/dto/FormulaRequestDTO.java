
package com.cesde.farmaciagrupo2.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record FormulaRequestDTO(

        @NotBlank(message = "El nombre de la fórmula es obligatorio")
        String nombre,

        @NotEmpty(message = "La fórmula debe contener al menos un medicamento")
        Set<@Positive(message = "Los ID de medicamentos deben ser mayores que cero") Long> medicamentoIds

) {
}
