
package com.cesde.farmaciagrupo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.farmaciagrupo2.model.entity.Venta;
import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Buscar ventas por estado
    List<Venta> findByEstado(EstadoVenta estado);

    // Buscar ventas cuyo total sea superior a un valor
    List<Venta> findByTotalGreaterThan(Double total);

}
