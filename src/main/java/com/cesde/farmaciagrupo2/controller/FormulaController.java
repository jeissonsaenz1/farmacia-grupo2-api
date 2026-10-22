package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.dto.FormulaRequestDTO;
import com.cesde.farmaciagrupo2.dto.FormulaResponseDTO;
import com.cesde.farmaciagrupo2.service.FormulaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formulas")
public class FormulaController {

    private final FormulaService formulaService;

    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @PostMapping
    public ResponseEntity<FormulaResponseDTO> crear(
            @RequestBody FormulaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(formulaService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<FormulaResponseDTO>> listar() {
        return ResponseEntity.ok(formulaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormulaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(formulaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormulaResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody FormulaRequestDTO dto) {

        return ResponseEntity.ok(
                formulaService.actualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        formulaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}