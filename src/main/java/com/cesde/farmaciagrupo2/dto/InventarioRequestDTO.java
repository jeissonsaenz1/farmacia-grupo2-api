package com.cesde.farmaciagrupo2.dto;

import java.time.LocalDate;

public record InventarioRequestDTO(
        Integer stock,
        String lote,
        LocalDate fechaVencimiento,
        Long medicamentoId
) {
}
