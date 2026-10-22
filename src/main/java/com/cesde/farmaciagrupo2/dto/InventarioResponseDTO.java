package com.cesde.farmaciagrupo2.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record InventarioResponseDTO(
        Long id,
        Integer stock,
        String lote,
        LocalDate fechaVencimiento,
        Long medicamentoId,
        String medicamentoNombre,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        Boolean estadoActivo
) {
}
