
package com.cesde.farmaciagrupo2.dto;

public record MedicamentoRequestDTO(
        String nombre,
        Double precio,
        Long laboratorioId
) {
}
