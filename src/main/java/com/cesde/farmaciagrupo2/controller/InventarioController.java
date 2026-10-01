package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.model.entity.Inventario;
import com.cesde.farmaciagrupo2.service.InventarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(
            @RequestBody Inventario inventario) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventarioService.guardar(inventario));
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(
            @PathVariable Long id) {

        return inventarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<Inventario> buscarPorMedicamento(
            @PathVariable Long medicamentoId) {

        return inventarioService.buscarPorMedicamento(medicamentoId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(
            @PathVariable Long id,
            @RequestBody Inventario inventario) {

        return ResponseEntity.ok(
                inventarioService.actualizar(id, inventario)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        inventarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}