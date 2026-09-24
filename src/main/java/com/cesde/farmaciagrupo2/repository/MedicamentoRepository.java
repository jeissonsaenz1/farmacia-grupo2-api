package com.cesde.farmaciagrupo2.repository;

import com.cesde.farmaciagrupo2.model.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);

    List<Medicamento> findByLaboratorioId(Long laboratorioId);

    List<Medicamento> findByPrecioBetween(Double precioMinimo, Double precioMaximo);
}