
package com.cesde.farmaciagrupo2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesde.farmaciagrupo2.dto.DetalleVentaRequestDTO;
import com.cesde.farmaciagrupo2.dto.DetalleVentaResponseDTO;
import com.cesde.farmaciagrupo2.exception.RecursoNoEncontradoException;
import com.cesde.farmaciagrupo2.exception.ReglaDeNegocioException;
import com.cesde.farmaciagrupo2.model.entity.DetalleVenta;
import com.cesde.farmaciagrupo2.model.entity.Medicamento;
import com.cesde.farmaciagrupo2.model.entity.Venta;
import com.cesde.farmaciagrupo2.repository.DetalleVentaRepository;
import com.cesde.farmaciagrupo2.repository.MedicamentoRepository;
import com.cesde.farmaciagrupo2.repository.VentaRepository;

@Service
@Transactional
public class DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;
    private final VentaRepository ventaRepository;
    private final MedicamentoRepository medicamentoRepository;

    public DetalleVentaService(
            DetalleVentaRepository detalleVentaRepository,
            VentaRepository ventaRepository,
            MedicamentoRepository medicamentoRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.ventaRepository = ventaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public DetalleVentaResponseDTO guardar(DetalleVentaRequestDTO dto) {
        validarDatos(dto);

        Venta venta = buscarVenta(dto.ventaId());
        Medicamento medicamento = buscarMedicamento(dto.medicamentoId());

        DetalleVenta detalle = new DetalleVenta();
        detalle.setCantidad(dto.cantidad());
        detalle.setSubtotal(dto.subtotal());
        detalle.setVenta(venta);
        detalle.setMedicamento(medicamento);

        return convertirADTO(detalleVentaRepository.save(detalle));
    }

    @Transactional(readOnly = true)
    public List<DetalleVentaResponseDTO> listar() {
        return detalleVentaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DetalleVentaResponseDTO buscarPorId(Long id) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Detalle de venta no encontrado."
                ));

        return convertirADTO(detalle);
    }

    public DetalleVentaResponseDTO actualizar(
            Long id,
            DetalleVentaRequestDTO dto) {

        validarDatos(dto);

        DetalleVenta existente = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Detalle de venta no encontrado."
                ));

        Venta venta = buscarVenta(dto.ventaId());
        Medicamento medicamento = buscarMedicamento(dto.medicamentoId());

        existente.setCantidad(dto.cantidad());
        existente.setSubtotal(dto.subtotal());
        existente.setVenta(venta);
        existente.setMedicamento(medicamento);

        return convertirADTO(detalleVentaRepository.save(existente));
    }

    public void eliminar(Long id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "Detalle de venta no encontrado."
            );
        }

        detalleVentaRepository.deleteById(id);
    }

    private Venta buscarVenta(Long ventaId) {
        return ventaRepository.findById(ventaId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Venta no encontrada."
                ));
    }

    private Medicamento buscarMedicamento(Long medicamentoId) {
        return medicamentoRepository.findById(medicamentoId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Medicamento no encontrado."
                ));
    }

    private void validarDatos(DetalleVentaRequestDTO dto) {
        if (dto == null) {
            throw new ReglaDeNegocioException(
                    "Los datos del detalle de venta son obligatorios."
            );
        }

        if (dto.cantidad() == null || dto.cantidad() <= 0) {
            throw new ReglaDeNegocioException(
                    "La cantidad debe ser mayor que cero."
            );
        }

        if (dto.subtotal() == null || dto.subtotal() < 0) {
            throw new ReglaDeNegocioException(
                    "El subtotal no puede ser negativo y es obligatorio."
            );
        }

        if (dto.ventaId() == null) {
            throw new ReglaDeNegocioException(
                    "La venta es obligatoria."
            );
        }

        if (dto.medicamentoId() == null) {
            throw new ReglaDeNegocioException(
                    "El medicamento es obligatorio."
            );
        }
    }

    private DetalleVentaResponseDTO convertirADTO(
            DetalleVenta detalle) {

        Venta venta = detalle.getVenta();
        Medicamento medicamento = detalle.getMedicamento();

        return new DetalleVentaResponseDTO(
                detalle.getId(),
                detalle.getCantidad(),
                detalle.getSubtotal(),
                venta != null ? venta.getId() : null,
                medicamento != null ? medicamento.getId() : null,
                medicamento != null ? medicamento.getNombre() : null,
                detalle.getFechaCreacion(),
                detalle.getFechaActualizacion(),
                detalle.getEstadoActivo()
        );
    }
}
