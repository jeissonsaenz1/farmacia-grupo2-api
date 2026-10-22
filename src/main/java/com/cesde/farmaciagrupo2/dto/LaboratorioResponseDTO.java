
package com.cesde.farmaciagrupo2.dto;

import java.time.LocalDateTime;

public record LaboratorioResponseDTO(
        Long id,
        String nombre,
        String calle,
        String ciudad,
        String pais,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        Boolean estadoActivo
) {
}

