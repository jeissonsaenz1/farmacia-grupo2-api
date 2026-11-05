
package com.cesde.farmaciagrupo2.dto;

import jakarta.validation.constraints.NotBlank;

public record LaboratorioRequestDTO(

        @NotBlank(message = "El nombre del laboratorio es obligatorio")
        String nombre,

        @NotBlank(message = "La calle es obligatoria")
        String calle,

        @NotBlank(message = "La ciudad es obligatoria")
        String ciudad,

        @NotBlank(message = "El país es obligatorio")
        String pais

) {
}
