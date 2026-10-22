
package com.cesde.farmaciagrupo2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesde.farmaciagrupo2.dto.MedicamentoRequestDTO;
import com.cesde.farmaciagrupo2.dto.MedicamentoResponseDTO;
import com.cesde.farmaciagrupo2.exception.RecursoNoEncontradoException;
import com.cesde.farmaciagrupo2.exception.ReglaDeNegocioException;
import com.cesde.farmaciagrupo2.model.entity.Laboratorio;
import com.cesde.farmaciagrupo2.model.entity.Medicamento;
import com.cesde.farmaciagrupo2.repository.LaboratorioRepository;
import com.cesde.farmaciagrupo2.repository.MedicamentoRepository;

@Service
@Transactional
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;
    private final LaboratorioRepository laboratorioRepository;

    public MedicamentoService(
            MedicamentoRepository medicamentoRepository,
            LaboratorioRepository laboratorioRepository) {
        this.medicamentoRepository = medicamentoRepository;
        this.laboratorioRepository = laboratorioRepository;
    }

    public MedicamentoResponseDTO guardar(MedicamentoRequestDTO dto) {
        validarDatos(dto);

        Laboratorio laboratorio = buscarLaboratorio(dto.laboratorioId());

        Medicamento medicamento = new Medicamento();
        medicamento.setNombre(dto.nombre().trim());
        medicamento.setPrecio(dto.precio());
        medicamento.setLaboratorio(laboratorio);

        return convertirADTO(medicamentoRepository.save(medicamento));
    }

    @Transactional(readOnly = true)
    public List<MedicamentoResponseDTO> listar() {
        return medicamentoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MedicamentoResponseDTO buscarPorId(Long id) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Medicamento no encontrado."
                ));

        return convertirADTO(medicamento);
    }

    @Transactional(readOnly = true)
    public List<MedicamentoResponseDTO> buscarPorNombre(String nombre) {
        return medicamentoRepository
                .findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicamentoResponseDTO> buscarPorLaboratorio(
            Long laboratorioId) {
        return medicamentoRepository.findByLaboratorioId(laboratorioId)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MedicamentoResponseDTO> buscarPorRangoPrecio(
            Double precioMinimo,
            Double precioMaximo) {

        if (precioMinimo == null || precioMaximo == null
                || precioMinimo < 0
                || precioMaximo < precioMinimo) {
            throw new ReglaDeNegocioException(
                    "El rango de precios no es válido."
            );
        }

        return medicamentoRepository.findByPrecioBetween(
                        precioMinimo, precioMaximo)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public MedicamentoResponseDTO actualizar(
            Long id,
            MedicamentoRequestDTO dto) {

        validarDatos(dto);

        Medicamento existente = medicamentoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Medicamento no encontrado."
                ));

        Laboratorio laboratorio = buscarLaboratorio(dto.laboratorioId());

        existente.setNombre(dto.nombre().trim());
        existente.setPrecio(dto.precio());
        existente.setLaboratorio(laboratorio);

        return convertirADTO(medicamentoRepository.save(existente));
    }

    public void eliminar(Long id) {
        if (!medicamentoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "Medicamento no encontrado."
            );
        }

        medicamentoRepository.deleteById(id);
    }

    private void validarDatos(MedicamentoRequestDTO dto) {
        if (dto == null) {
            throw new ReglaDeNegocioException(
                    "Los datos del medicamento son obligatorios."
            );
        }

        if (dto.nombre() == null || dto.nombre().isBlank()) {
            throw new ReglaDeNegocioException(
                    "El nombre del medicamento es obligatorio."
            );
        }

        if (dto.precio() == null || dto.precio() <= 0) {
            throw new ReglaDeNegocioException(
                    "El precio del medicamento debe ser mayor que cero."
            );
        }

        if (dto.laboratorioId() == null) {
            throw new ReglaDeNegocioException(
                    "El laboratorio es obligatorio."
            );
        }
    }

    private Laboratorio buscarLaboratorio(Long laboratorioId) {
        return laboratorioRepository.findById(laboratorioId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Laboratorio no encontrado."
                ));
    }

    private MedicamentoResponseDTO convertirADTO(
            Medicamento medicamento) {

        Laboratorio laboratorio = medicamento.getLaboratorio();

        return new MedicamentoResponseDTO(
                medicamento.getId(),
                medicamento.getNombre(),
                medicamento.getPrecio(),
                laboratorio != null ? laboratorio.getId() : null,
                laboratorio != null ? laboratorio.getNombre() : null,
                medicamento.getFechaCreacion(),
                medicamento.getFechaActualizacion(),
                medicamento.getEstadoActivo()
        );
    }
}
