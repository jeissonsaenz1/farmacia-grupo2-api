package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.model.entity.Medicamento;
import com.cesde.farmaciagrupo2.service.MedicamentoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PostMapping
    public ResponseEntity<Medicamento> crear(
            @RequestBody Medicamento medicamento) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicamentoService.guardar(medicamento));
    }

    @GetMapping
    public ResponseEntity<List<Medicamento>> listar() {
        return ResponseEntity.ok(medicamentoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscarPorId(
            @PathVariable Long id) {

        return medicamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Medicamento>> buscarPorNombre(
            @RequestParam String nombre) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorNombre(nombre)
        );
    }

    @GetMapping("/laboratorio/{laboratorioId}")
    public ResponseEntity<List<Medicamento>> buscarPorLaboratorio(
            @PathVariable Long laboratorioId) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorLaboratorio(laboratorioId)
        );
    }

    @GetMapping("/rango-precio")
    public ResponseEntity<List<Medicamento>> buscarPorRangoPrecio(
            @RequestParam Double precioMinimo,
            @RequestParam Double precioMaximo) {

        return ResponseEntity.ok(
                medicamentoService.buscarPorRangoPrecio(
                        precioMinimo,
                        precioMaximo
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> actualizar(
            @PathVariable Long id,
            @RequestBody Medicamento medicamento) {

        return ResponseEntity.ok(
                medicamentoService.actualizar(id, medicamento)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        medicamentoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}