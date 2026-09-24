package com.cesde.farmaciagrupo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.farmaciagrupo2.model.entity.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}