package com.cesde.farmaciagrupo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MedicamentoRequestDTO(

        @NotBlank(message = "El nombre del medicamento es obligatorio")
        String nombre,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor que cero")
        Double precio,

        @NotNull(message = "El laboratorio es obligatorio")
        @Positive(message = "El ID del laboratorio debe ser mayor que cero")
        Long laboratorioId

) {
}