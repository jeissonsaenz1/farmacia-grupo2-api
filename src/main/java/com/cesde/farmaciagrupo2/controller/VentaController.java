package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.dto.VentaRequestDTO;
import com.cesde.farmaciagrupo2.dto.VentaResponseDTO;
import com.cesde.farmaciagrupo2.model.enums.EstadoVenta;
import com.cesde.farmaciagrupo2.service.VentaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> crear(
            @RequestBody VentaRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(ventaService.buscarPorId(id));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<VentaResponseDTO>> buscarPorEstado(
            @RequestParam EstadoVenta estado) {

        return ResponseEntity.ok(
                ventaService.buscarPorEstado(estado)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody VentaRequestDTO dto) {

        return ResponseEntity.ok(
                ventaService.actualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        ventaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}