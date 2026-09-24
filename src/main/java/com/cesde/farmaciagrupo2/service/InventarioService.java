package com.cesde.farmaciagrupo2.service;

import com.cesde.farmaciagrupo2.model.entity.Inventario;
import com.cesde.farmaciagrupo2.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public Inventario guardar(Inventario inventario) {

        validarStock(inventario);
        validarFechaVencimiento(inventario);

        return inventarioRepository.save(inventario);
    }

    // Regla de negocio 2:
    // El stock no puede ser negativo.
    private void validarStock(Inventario inventario) {

        if (inventario.getStock() < 0) {
            throw new IllegalArgumentException(
                    "El stock del inventario no puede ser negativo.");
        }
    }

    // Regla de negocio 3:
    // No se puede registrar un medicamento vencido.
    private void validarFechaVencimiento(Inventario inventario) {

        if (inventario.getFechaVencimiento() == null) {
            throw new IllegalArgumentException(
                    "La fecha de vencimiento es obligatoria.");
        }

        if (inventario.getFechaVencimiento().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "No se puede registrar un medicamento vencido.");
        }
    }

    public Optional<Inventario> buscarPorMedicamento(Long medicamentoId) {
        return inventarioRepository.findByMedicamentoId(medicamentoId);
    }
}