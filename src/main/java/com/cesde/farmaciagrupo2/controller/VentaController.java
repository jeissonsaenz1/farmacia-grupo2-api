package com.cesde.farmaciagrupo2.controller;

import com.cesde.farmaciagrupo2.model.entity.Venta;
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
    public ResponseEntity<Venta> crear(
            @RequestBody Venta venta) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaService.guardar(venta));
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listar() {
        return ResponseEntity.ok(ventaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ventaService.buscarPorId(id)
        );
    }

    @GetMapping("/estado")
    public ResponseEntity<List<Venta>> buscarPorEstado(
            @RequestParam EstadoVenta estado) {

        return ResponseEntity.ok(
                ventaService.buscarPorEstado(estado)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(
            @PathVariable Long id,
            @RequestBody Venta venta) {

        return ResponseEntity.ok(
                ventaService.actualizar(id, venta)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        ventaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}