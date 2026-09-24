package com.cesde.farmaciagrupo2.repository;

import com.cesde.farmaciagrupo2.model.entity.Venta;
import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByEstado(EstadoVenta estado);

}
