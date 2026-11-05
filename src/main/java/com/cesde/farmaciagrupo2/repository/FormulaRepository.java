
package com.cesde.farmaciagrupo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.farmaciagrupo2.model.entity.Formula;

public interface FormulaRepository extends JpaRepository<Formula, Long> {

    // Buscar fórmulas por nombre
    List<Formula> findByNombreContainingIgnoreCase(String nombre);

    // Buscar fórmulas por el ID de un medicamento asociado
    List<Formula> findByMedicamentosId(Long medicamentoId);

}
