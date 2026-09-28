package com.cesde.farmaciagrupo2.service;

import com.cesde.farmaciagrupo2.model.entity.Formula;
import com.cesde.farmaciagrupo2.repository.FormulaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FormulaService {

    private final FormulaRepository formulaRepository;

    public FormulaService(FormulaRepository formulaRepository) {
        this.formulaRepository = formulaRepository;
    }

    public Formula guardar(Formula formula) {
        return formulaRepository.save(formula);
    }

    public List<Formula> listar() {
        return formulaRepository.findAll();
    }

    public Formula buscarPorId(Long id) {
        return formulaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Fórmula no encontrada."
                ));
    }

    public Formula actualizar(Long id, Formula formula) {

        Formula formulaExistente = formulaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Fórmula no encontrada."
                ));

        formulaExistente.setNombre(formula.getNombre());
        formulaExistente.setMedicamentos(formula.getMedicamentos());

        return formulaRepository.save(formulaExistente);
    }

    public void eliminar(Long id) {

        if (!formulaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Fórmula no encontrada."
            );
        }

        formulaRepository.deleteById(id);
    }
}
