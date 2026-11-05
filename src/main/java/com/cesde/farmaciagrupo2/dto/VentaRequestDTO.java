
package com.cesde.farmaciagrupo2.dto;

import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VentaRequestDTO(

        @NotNull(message = "El total de la venta es obligatorio")
        @Positive(message = "El total debe ser mayor que cero")
        Double total,

        @NotNull(message = "El estado de la venta es obligatorio")
        EstadoVenta estado

) {
}
