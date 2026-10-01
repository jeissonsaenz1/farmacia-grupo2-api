package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.model.entity.Laboratorio;
import com.cesde.farmaciagrupo2.service.LaboratorioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @PostMapping
    public ResponseEntity<Laboratorio> crear(
            @RequestBody Laboratorio laboratorio) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(laboratorioService.guardar(laboratorio));
    }

    @GetMapping
    public ResponseEntity<List<Laboratorio>> listar() {
        return ResponseEntity.ok(laboratorioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                laboratorioService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> actualizar(
            @PathVariable Long id,
            @RequestBody Laboratorio laboratorio) {

        return ResponseEntity.ok(
                laboratorioService.actualizar(id, laboratorio)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        laboratorioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}