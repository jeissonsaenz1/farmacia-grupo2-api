package com.cesde.farmaciagrupo2.service;

import com.cesde.farmaciagrupo2.model.entity.Inventario;
import com.cesde.farmaciagrupo2.repository.InventarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
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

    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> buscarPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    public Optional<Inventario> buscarPorMedicamento(Long medicamentoId) {
        return inventarioRepository.findByMedicamentoId(medicamentoId);
    }

    public Inventario actualizar(Long id, Inventario inventario) {

        Inventario inventarioExistente = inventarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Inventario no encontrado."
                ));

        validarStock(inventario);
        validarFechaVencimiento(inventario);

        inventarioExistente.setStock(inventario.getStock());
        inventarioExistente.setLote(inventario.getLote());
        inventarioExistente.setFechaVencimiento(
                inventario.getFechaVencimiento()
        );
        inventarioExistente.setMedicamento(inventario.getMedicamento());

        return inventarioRepository.save(inventarioExistente);
    }

    public void eliminar(Long id) {

        if (!inventarioRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Inventario no encontrado."
            );
        }

        inventarioRepository.deleteById(id);
    }

    // Regla de negocio 2:
    // El stock no puede ser negativo.
    private void validarStock(Inventario inventario) {

        if (inventario.getStock() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El stock del inventario no puede ser negativo."
            );
        }
    }

    // Regla de negocio 3:
    // No se puede registrar un medicamento vencido.
    private void validarFechaVencimiento(Inventario inventario) {

        if (inventario.getFechaVencimiento() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La fecha de vencimiento es obligatoria."
            );
        }

        if (inventario.getFechaVencimiento().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se puede registrar un medicamento vencido."
            );
        }
    }
}