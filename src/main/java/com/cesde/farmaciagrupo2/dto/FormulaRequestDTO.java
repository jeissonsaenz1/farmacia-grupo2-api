package com.cesde.farmaciagrupo2.dto;

import java.util.Set;

public record FormulaRequestDTO(
        String nombre,
        Set<Long> medicamentoIds
) {
}