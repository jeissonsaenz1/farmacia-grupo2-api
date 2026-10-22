package com.cesde.farmaciagrupo2.dto;

import java.time.LocalDateTime;

public record DetalleVentaResponseDTO(
        Long id,
        Integer cantidad,
        Double subtotal,
        Long ventaId,
        Long medicamentoId,
        String medicamentoNombre,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        Boolean estadoActivo
) {
}