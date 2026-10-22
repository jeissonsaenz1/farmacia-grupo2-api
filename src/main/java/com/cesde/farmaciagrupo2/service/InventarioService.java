package com.cesde.farmaciagrupo2.service;

import com.cesde.farmaciagrupo2.dto.InventarioRequestDTO;
import com.cesde.farmaciagrupo2.dto.InventarioResponseDTO;
import com.cesde.farmaciagrupo2.exception.RecursoNoEncontradoException;
import com.cesde.farmaciagrupo2.exception.ReglaDeNegocioException;
import com.cesde.farmaciagrupo2.model.entity.Inventario;
import com.cesde.farmaciagrupo2.model.entity.Medicamento;
import com.cesde.farmaciagrupo2.repository.InventarioRepository;
import com.cesde.farmaciagrupo2.repository.MedicamentoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final MedicamentoRepository medicamentoRepository;

    public InventarioService(
            InventarioRepository inventarioRepository,
            MedicamentoRepository medicamentoRepository) {
        this.inventarioRepository = inventarioRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public InventarioResponseDTO guardar(InventarioRequestDTO dto) {
        validarDatos(dto);

        Medicamento medicamento = buscarMedicamento(dto.medicamentoId());

        Inventario inventario = new Inventario();
        inventario.setStock(dto.stock());
        inventario.setLote(dto.lote());
        inventario.setFechaVencimiento(dto.fechaVencimiento());
        inventario.setMedicamento(medicamento);

        Inventario guardado = inventarioRepository.save(inventario);

        return convertirADTO(guardado);
    }

    @Transactional(readOnly = true)
    public List<InventarioResponseDTO> listar() {
        return inventarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<InventarioResponseDTO> buscarPorId(Long id) {
        return inventarioRepository.findById(id)
                .map(this::convertirADTO);
    }

    @Transactional(readOnly = true)
    public Optional<InventarioResponseDTO> buscarPorMedicamento(
            Long medicamentoId) {
        return inventarioRepository.findByMedicamentoId(medicamentoId)
                .map(this::convertirADTO);
    }

    public InventarioResponseDTO actualizar(
            Long id,
            InventarioRequestDTO dto) {

        validarDatos(dto);

        Inventario existente = inventarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Inventario no encontrado."
                ));

        Medicamento medicamento = buscarMedicamento(dto.medicamentoId());

        existente.setStock(dto.stock());
        existente.setLote(dto.lote());
        existente.setFechaVencimiento(dto.fechaVencimiento());
        existente.setMedicamento(medicamento);

        Inventario actualizado = inventarioRepository.save(existente);

        return convertirADTO(actualizado);
    }

    public void eliminar(Long id) {
        if (!inventarioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "Inventario no encontrado."
            );
        }

        inventarioRepository.deleteById(id);
    }

    private Medicamento buscarMedicamento(Long medicamentoId) {
        if (medicamentoId == null) {
            throw new ReglaDeNegocioException(
                    "El medicamento es obligatorio."
            );
        }

        return medicamentoRepository.findById(medicamentoId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Medicamento no encontrado."
                ));
    }

    private void validarDatos(InventarioRequestDTO dto) {
        if (dto == null) {
            throw new ReglaDeNegocioException(
                    "Los datos del inventario son obligatorios."
            );
        }

        if (dto.stock() == null || dto.stock() < 0) {
            throw new ReglaDeNegocioException(
                    "El stock del inventario no puede ser negativo y es obligatorio."
            );
        }

        if (dto.lote() == null || dto.lote().isBlank()) {
            throw new ReglaDeNegocioException(
                    "El lote es obligatorio."
            );
        }

        if (dto.lote().length() > 50) {
            throw new ReglaDeNegocioException(
                    "El lote no puede superar los 50 caracteres."
            );
        }

        if (dto.fechaVencimiento() == null) {
            throw new ReglaDeNegocioException(
                    "La fecha de vencimiento es obligatoria."
            );
        }

        if (dto.fechaVencimiento().isBefore(LocalDate.now())) {
            throw new ReglaDeNegocioException(
                    "No se puede registrar un medicamento vencido."
            );
        }

        if (dto.medicamentoId() == null) {
            throw new ReglaDeNegocioException(
                    "El medicamento es obligatorio."
            );
        }
    }

    private InventarioResponseDTO convertirADTO(Inventario inventario) {
        Medicamento medicamento = inventario.getMedicamento();

        return new InventarioResponseDTO(
                inventario.getId(),
                inventario.getStock(),
                inventario.getLote(),
                inventario.getFechaVencimiento(),
                medicamento != null ? medicamento.getId() : null,
                medicamento != null ? medicamento.getNombre() : null,
                inventario.getFechaCreacion(),
                inventario.getFechaActualizacion(),
                inventario.getEstadoActivo()
        );
    }
}