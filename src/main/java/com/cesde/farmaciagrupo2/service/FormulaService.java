package com.cesde.farmaciagrupo2.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesde.farmaciagrupo2.dto.FormulaRequestDTO;
import com.cesde.farmaciagrupo2.dto.FormulaResponseDTO;
import com.cesde.farmaciagrupo2.exception.RecursoNoEncontradoException;
import com.cesde.farmaciagrupo2.exception.ReglaDeNegocioException;
import com.cesde.farmaciagrupo2.model.entity.Formula;
import com.cesde.farmaciagrupo2.model.entity.Medicamento;
import com.cesde.farmaciagrupo2.repository.FormulaRepository;
import com.cesde.farmaciagrupo2.repository.MedicamentoRepository;

@Service
@Transactional
public class FormulaService {

    private final FormulaRepository formulaRepository;
    private final MedicamentoRepository medicamentoRepository;

    public FormulaService(
            FormulaRepository formulaRepository,
            MedicamentoRepository medicamentoRepository) {
        this.formulaRepository = formulaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public FormulaResponseDTO guardar(FormulaRequestDTO dto) {
        validarDatos(dto);

        Formula formula = new Formula();
        formula.setNombre(dto.nombre().trim());
        formula.setMedicamentos(buscarMedicamentos(dto.medicamentoIds()));

        Formula guardada = formulaRepository.save(formula);

        return convertirADTO(guardada);
    }

    @Transactional(readOnly = true)
    public List<FormulaResponseDTO> listar() {
        return formulaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public FormulaResponseDTO buscarPorId(Long id) {
        Formula formula = formulaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Formula no encontrada."
                ));

        return convertirADTO(formula);
    }

    public FormulaResponseDTO actualizar(
            Long id,
            FormulaRequestDTO dto) {

        validarDatos(dto);

        Formula existente = formulaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Formula no encontrada."
                ));

        existente.setNombre(dto.nombre().trim());
        existente.setMedicamentos(
                buscarMedicamentos(dto.medicamentoIds())
        );

        Formula actualizada = formulaRepository.save(existente);

        return convertirADTO(actualizada);
    }

    public void eliminar(Long id) {
        if (!formulaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException(
                    "Formula no encontrada."
            );
        }

        formulaRepository.deleteById(id);
    }

    private void validarDatos(FormulaRequestDTO dto) {
        if (dto == null) {
            throw new ReglaDeNegocioException(
                    "Los datos de la formula son obligatorios."
            );
        }

        if (dto.nombre() == null || dto.nombre().isBlank()) {
            throw new ReglaDeNegocioException(
                    "El nombre de la formula es obligatorio."
            );
        }

        if (dto.nombre().trim().length() > 100) {
            throw new ReglaDeNegocioException(
                    "El nombre de la formula no puede superar los 100 caracteres."
            );
        }
    }

    private Set<Medicamento> buscarMedicamentos(Set<Long> medicamentoIds) {
        Set<Medicamento> medicamentos = new LinkedHashSet<>();

        if (medicamentoIds == null || medicamentoIds.isEmpty()) {
            return medicamentos;
        }

        for (Long medicamentoId : medicamentoIds) {
            if (medicamentoId == null) {
                throw new ReglaDeNegocioException(
                        "Los identificadores de medicamentos no pueden ser nulos."
                );
            }

            Medicamento medicamento = medicamentoRepository
                    .findById(medicamentoId)
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "Medicamento no encontrado con ID: " + medicamentoId
                    ));

            medicamentos.add(medicamento);
        }

        return medicamentos;
    }

    private FormulaResponseDTO convertirADTO(Formula formula) {
        Set<Medicamento> medicamentos = formula.getMedicamentos();

        Set<Long> medicamentoIds = medicamentos == null
                ? new LinkedHashSet<>()
                : medicamentos.stream()
                        .map(Medicamento::getId)
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<String> medicamentosNombres = medicamentos == null
                ? new LinkedHashSet<>()
                : medicamentos.stream()
                        .map(Medicamento::getNombre)
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        return new FormulaResponseDTO(
                formula.getId(),
                formula.getNombre(),
                medicamentoIds,
                medicamentosNombres,
                formula.getFechaCreacion(),
                formula.getFechaActualizacion(),
                formula.getEstadoActivo()
        );
    }
}