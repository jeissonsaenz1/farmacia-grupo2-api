package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.dto.DetalleVentaRequestDTO;
import com.cesde.farmaciagrupo2.dto.DetalleVentaResponseDTO;
import com.cesde.farmaciagrupo2.service.DetalleVentaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(
            DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @PostMapping
    public ResponseEntity<DetalleVentaResponseDTO> crear(
            @RequestBody DetalleVentaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(detalleVentaService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaResponseDTO>> listar() {
        return ResponseEntity.ok(detalleVentaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                detalleVentaService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody DetalleVentaRequestDTO dto) {

        return ResponseEntity.ok(
                detalleVentaService.actualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        detalleVentaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}