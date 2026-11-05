
package com.cesde.farmaciagrupo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesde.farmaciagrupo2.model.entity.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

    // Buscar laboratorios por nombre
    List<Laboratorio> findByNombreContainingIgnoreCase(String nombre);

    // Buscar laboratorios por ciudad
    List<Laboratorio> findByDireccion_CiudadContainingIgnoreCase(String ciudad);

}
