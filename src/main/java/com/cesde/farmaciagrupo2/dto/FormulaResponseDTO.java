package com.cesde.farmaciagrupo2.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record FormulaResponseDTO(
        Long id,
        String nombre,
        Set<Long> medicamentoIds,
        Set<String> medicamentosNombres,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        Boolean estadoActivo
) {
}
