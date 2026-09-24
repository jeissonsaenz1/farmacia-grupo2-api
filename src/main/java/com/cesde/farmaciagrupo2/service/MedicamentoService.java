package com.cesde.farmaciagrupo2.service;

import com.cesde.farmaciagrupo2.model.entity.Medicamento;
import com.cesde.farmaciagrupo2.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    public Medicamento guardar(Medicamento medicamento) {

        // Regla de negocio 1:
        // El precio debe ser mayor que cero.
        if (medicamento.getPrecio() == null || medicamento.getPrecio() <= 0) {
            throw new IllegalArgumentException(
                    "El precio del medicamento debe ser mayor que cero.");
        }

        return medicamentoRepository.save(medicamento);
    }

    public Optional<Medicamento> buscarPorId(Long id) {
        return medicamentoRepository.findById(id);
    }

    public List<Medicamento> buscarPorNombre(String nombre) {
        return medicamentoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Medicamento> buscarPorLaboratorio(Long laboratorioId) {
        return medicamentoRepository.findByLaboratorioId(laboratorioId);
    }

    public List<Medicamento> buscarPorRangoPrecio(
            Double precioMinimo,
            Double precioMaximo) {

        return medicamentoRepository.findByPrecioBetween(
                precioMinimo,
                precioMaximo);
    }
}