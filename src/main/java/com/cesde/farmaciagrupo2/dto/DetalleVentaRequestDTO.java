
package com.cesde.farmaciagrupo2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetalleVentaRequestDTO(

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor que cero")
        Integer cantidad,

        @NotNull(message = "El subtotal es obligatorio")
        @Positive(message = "El subtotal debe ser mayor que cero")
        Double subtotal,

        @NotNull(message = "El ID de la venta es obligatorio")
        @Positive(message = "El ID de la venta debe ser mayor que cero")
        Long ventaId,

        @NotNull(message = "El ID del medicamento es obligatorio")
        @Positive(message = "El ID del medicamento debe ser mayor que cero")
        Long medicamentoId

) {
}
