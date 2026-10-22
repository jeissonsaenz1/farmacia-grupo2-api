package com.cesde.farmaciagrupo2.dto;

import java.time.LocalDateTime;

import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;

public record VentaResponseDTO(
        Long id,
        Double total,
        EstadoVenta estado,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion,
        Boolean estadoActivo
) {
}
