
package com.cesde.farmaciagrupo2.dto;

import java.time.LocalDateTime;

public record MedicamentoResponseDTO(
        Long id,
        String nombre,
        Double precio,
        Long laboratorioId,
        String laboratorioNombre,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        Boolean estadoActivo
) {
}


