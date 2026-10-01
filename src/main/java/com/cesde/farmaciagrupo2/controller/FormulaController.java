package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.model.entity.Formula;
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
    public ResponseEntity<Formula> crear(
            @RequestBody Formula formula) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(formulaService.guardar(formula));
    }

    @GetMapping
    public ResponseEntity<List<Formula>> listar() {
        return ResponseEntity.ok(formulaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formula> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                formulaService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formula> actualizar(
            @PathVariable Long id,
            @RequestBody Formula formula) {

        return ResponseEntity.ok(
                formulaService.actualizar(id, formula)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        formulaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}