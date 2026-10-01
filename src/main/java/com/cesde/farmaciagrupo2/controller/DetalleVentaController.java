package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.model.entity.DetalleVenta;
import com.cesde.farmaciagrupo2.service.DetalleVentaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @PostMapping
    public ResponseEntity<DetalleVenta> crear(
            @RequestBody DetalleVenta detalleVenta) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(detalleVentaService.guardar(detalleVenta));
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() {
        return ResponseEntity.ok(detalleVentaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                detalleVentaService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> actualizar(
            @PathVariable Long id,
            @RequestBody DetalleVenta detalleVenta) {

        return ResponseEntity.ok(
                detalleVentaService.actualizar(id, detalleVenta)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        detalleVentaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}