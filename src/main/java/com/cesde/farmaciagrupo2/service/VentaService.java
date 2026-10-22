package com.cesde.farmaciagrupo2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesde.farmaciagrupo2.dto.VentaRequestDTO;
import com.cesde.farmaciagrupo2.dto.VentaResponseDTO;
import com.cesde.farmaciagrupo2.exception.RecursoNoEncontradoException;
import com.cesde.farmaciagrupo2.exception.ReglaDeNegocioException;
import com.cesde.farmaciagrupo2.model.entity.Venta;
import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;
import com.cesde.farmaciagrupo2.repository.VentaRepository;

@Service
@Transactional
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public VentaResponseDTO guardar(VentaRequestDTO dto) {
        validarDatos(dto);

        Venta venta = new Venta();
        venta.setTotal(dto.total());
        venta.setEstado(dto.estado());

        Venta guardada = ventaRepository.save(venta);

        return convertirADTO(guardada);
    }

    @Transactional(readOnly = true)
    public List<VentaResponseDTO> listar() {
        return ventaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public VentaResponseDTO buscarPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Venta no encontrada."
                ));

        return convertirADTO(venta);
    }

    public VentaResponseDTO actualizar(Long id, VentaRequestDTO dto) {
        validarDatos(dto);

        Venta existente = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Venta no encontrada."
                ));

        existente.setTotal(dto.total());
        existente.setEstado(dto.estado());

        Venta actualizada = ventaRepository.save(existente);

        return convertirADTO(actualizada);
    }

    @Transactional(readOnly = true)
    public List<VentaResponseDTO> buscarPorEstado(EstadoVenta estado) {
        return ventaRepository.findByEstado(estado)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public void eliminar(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "Venta no encontrada."
            );
        }

        ventaRepository.deleteById(id);
    }

    private void validarDatos(VentaRequestDTO dto) {
        if (dto == null) {
            throw new ReglaDeNegocioException(
                    "Los datos de la venta son obligatorios."
            );
        }

        if (dto.total() == null || dto.total() < 0) {
            throw new ReglaDeNegocioException(
                    "El total de la venta no puede ser negativo y es obligatorio."
            );
        }

        if (dto.estado() == null) {
            throw new ReglaDeNegocioException(
                    "El estado de la venta es obligatorio."
            );
        }
    }

    private VentaResponseDTO convertirADTO(Venta venta) {
        return new VentaResponseDTO(
                venta.getId(),
                venta.getTotal(),
                venta.getEstado(),
                venta.getFechaCreacion(),
                venta.getFechaActualizacion(),
                venta.getEstadoActivo()
        );
    }
}