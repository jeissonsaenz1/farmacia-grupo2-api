
package com.cesde.farmaciagrupo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.farmaciagrupo2.model.entity.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    // Buscar detalles asociados a una venta
    List<DetalleVenta> findByVenta_Id(Long ventaId);

    // Buscar detalles asociados a un medicamento
    List<DetalleVenta> findByMedicamento_Id(Long medicamentoId);

}
