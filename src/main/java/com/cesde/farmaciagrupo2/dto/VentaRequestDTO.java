package com.cesde.farmaciagrupo2.dto;

import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;

public record VentaRequestDTO(
        Double total,
        EstadoVenta estado
) {
}