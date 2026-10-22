package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.dto.InventarioRequestDTO;
import com.cesde.farmaciagrupo2.dto.InventarioResponseDTO;
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
    public ResponseEntity<InventarioResponseDTO> crear(
            @RequestBody InventarioRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventarioService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return inventarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/medicamento/{medicamentoId}")
    public ResponseEntity<InventarioResponseDTO> buscarPorMedicamento(
            @PathVariable Long medicamentoId) {

        return inventarioService.buscarPorMedicamento(medicamentoId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody InventarioRequestDTO dto) {

        return ResponseEntity.ok(
                inventarioService.actualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        inventarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}