
package com.cesde.farmaciagrupo2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesde.farmaciagrupo2.dto.LaboratorioRequestDTO;
import com.cesde.farmaciagrupo2.dto.LaboratorioResponseDTO;
import com.cesde.farmaciagrupo2.exception.RecursoNoEncontradoException;
import com.cesde.farmaciagrupo2.model.embeddable.Direccion;
import com.cesde.farmaciagrupo2.model.entity.Laboratorio;
import com.cesde.farmaciagrupo2.repository.LaboratorioRepository;

@Service
@Transactional
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioService(
            LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    public LaboratorioResponseDTO guardar(LaboratorioRequestDTO dto) {
        Laboratorio laboratorio = new Laboratorio();

        laboratorio.setNombre(dto.nombre());
        laboratorio.setDireccion(convertirDireccion(dto));

        Laboratorio guardado = laboratorioRepository.save(laboratorio);

        return convertirADTO(guardado);
    }

    @Transactional(readOnly = true)
    public List<LaboratorioResponseDTO> listar() {
        return laboratorioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public LaboratorioResponseDTO buscarPorId(Long id) {
        Laboratorio laboratorio = laboratorioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Laboratorio no encontrado."));

        return convertirADTO(laboratorio);
    }

    public LaboratorioResponseDTO actualizar(
            Long id,
            LaboratorioRequestDTO dto) {

        Laboratorio existente = laboratorioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Laboratorio no encontrado."));

        existente.setNombre(dto.nombre());
        existente.setDireccion(convertirDireccion(dto));

        Laboratorio actualizado = laboratorioRepository.save(existente);

        return convertirADTO(actualizado);
    }

    public void eliminar(Long id) {
        if (!laboratorioRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "Laboratorio no encontrado.");
        }

        laboratorioRepository.deleteById(id);
    }

    private Direccion convertirDireccion(LaboratorioRequestDTO dto) {
        return Direccion.builder()
                .calle(dto.calle())
                .ciudad(dto.ciudad())
                .pais(dto.pais())
                .build();
    }

    private LaboratorioResponseDTO convertirADTO(
            Laboratorio laboratorio) {

        Direccion direccion = laboratorio.getDireccion();

        return new LaboratorioResponseDTO(
                laboratorio.getId(),
                laboratorio.getNombre(),
                direccion != null ? direccion.getCalle() : null,
                direccion != null ? direccion.getCiudad() : null,
                direccion != null ? direccion.getPais() : null,
                laboratorio.getFechaCreacion(),
                laboratorio.getFechaActualizacion(),
                laboratorio.getEstadoActivo()
        );
    }
}
