
package com.cesde.farmaciagrupo2.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.farmaciagrupo2.model.entity.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    // Buscar inventario por medicamento
    Optional<Inventario> findByMedicamentoId(Long medicamentoId);

    // Buscar inventarios con stock inferior a una cantidad
    List<Inventario> findByStockLessThan(Integer stock);

    // Buscar inventarios por número de lote
    List<Inventario> findByLoteContainingIgnoreCase(String lote);

}
