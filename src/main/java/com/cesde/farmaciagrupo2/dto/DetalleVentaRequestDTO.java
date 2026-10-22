package com.cesde.farmaciagrupo2.dto;

public record DetalleVentaRequestDTO(
        Integer cantidad,
        Double subtotal,
        Long ventaId,
        Long medicamentoId
) {
}